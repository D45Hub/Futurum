package com.futurumgame.base.gameinternals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.function.Consumer;

public class FactorySystem extends ViewGroup {

    private final LinkedList<LinkedList<FactoryNode<? extends Resource>>> root = new LinkedList<>();
    private final Paint backGround = new Paint();
    private final PointF ofs = new PointF();

    private int deviceWidth;


    private FactoryNode<? extends Resource> lastEdited;

    public FactorySystem(Context context) {
        this(context, null, 0);
    }

    public FactorySystem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FactorySystem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        backGround.setColor(Color.argb(255,19,19,19));
        backGround.setStyle(Paint.Style.FILL);
        final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point deviceDisplay = new Point();
        display.getSize(deviceDisplay);
        deviceWidth = deviceDisplay.x;
    }

    public <T extends Resource> void add(Factory<T> factory) {
        int enterLevel = factory.getLongestResourceTreePath().size();
        int maxLevel = root.size();
        if (enterLevel > maxLevel) {
            throw new InvalidParameterException("Level of resource is to high, so could not produce resource if factory would start.");
        }
        if (CollectionHelper.contains(root, depth -> containsTask(depth, factory))) {
            return;
        }
        boolean needsNewLevel = enterLevel == root.size();
        LinkedList<FactoryNode<? extends Resource>> level = needsNewLevel ? new LinkedList<>() : root.get(enterLevel);
        FactoryNode<? extends Resource> node = new FactoryNode<>(getContext(), factory, calculateOptimalPosition(enterLevel));
        level.add(node);
        lastEdited = node;
        if (needsNewLevel) {
            root.add(level);
        }
        addView(node);
    }

    public FactoryNode<? extends Resource> getLastEdited() {
        return lastEdited;
    }

    public void forEach(Consumer<FactoryNode<? extends Resource>> consumer) {
        for (LinkedList<FactoryNode<? extends Resource>> level : root) {
            for (FactoryNode<? extends Resource> node : level) {
                consumer.accept(node);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int curWidth, curHeight, curLeft, curTop, maxHeight;

        //get the available size of child view
        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();
        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int childWidth = childRight - childLeft;
        final int childHeight = childBottom - childTop;

        maxHeight = 0;
        curLeft = childLeft;
        curTop = childTop;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() == GONE)
                return;

            //Get the maximum size of the child
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST));
            curWidth = child.getMeasuredWidth();
            curHeight = child.getMeasuredHeight();
            //wrap is reach to the end
            if (curLeft + curWidth >= childRight) {
                curLeft = childLeft;
                curTop += maxHeight;
                maxHeight = 0;
            }
            //do the layout
            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
            //store the max height
            if (maxHeight < curHeight)
                maxHeight = curHeight;
            curLeft += curWidth;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Measurement will ultimately be computing these values.
        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        int mLeftWidth = 0;
        int rowCount = 0;

        // Iterate through all children, measuring them and computing our dimensions
        // from their size.
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            //TODO: tobedone
            //if(child instanceof FactoryNode){
            //    FactoryNode<?extends Resource> node = (FactoryNode)child;
            //    continue;
            //}

            // Measure the child.
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            maxWidth += Math.max(maxWidth, child.getMeasuredWidth());
            mLeftWidth += child.getMeasuredWidth();

            if ((mLeftWidth / deviceWidth) > rowCount) {
                maxHeight += child.getMeasuredHeight();
                rowCount++;
            } else {
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            }
            childState = combineMeasuredStates(childState, child.getMeasuredState());
        }

        // Check against our minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        // Report our final dimensions.
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private <T extends Resource> boolean containsTask(LinkedList<FactoryNode<? extends Resource>> level, Factory<T> factory) {
        return CollectionHelper.executeOnFirstIfTrue(level, node -> containsPredicate(node, factory), node -> changePrimaryFactory(node, factory));
    }

    private void changePrimaryFactory(FactoryNode<? extends Resource> node, Factory<? extends Resource> factory) {
        node.changePrimaryFactory(factory);
        lastEdited = node;
    }

    private static <T extends Resource> boolean containsPredicate(FactoryNode<? extends Resource> node, Factory<T> factory) {
        return node.getResourceName().contentEquals(factory.getResource().getName());
    }

    private PointF calculateOptimalPosition(int enterLevel) {
        return new PointF();
    }
}
