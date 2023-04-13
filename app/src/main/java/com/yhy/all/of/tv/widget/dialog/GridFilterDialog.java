package com.yhy.all.of.tv.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.yhy.all.of.tv.R;

import org.jetbrains.annotations.NotNull;

/**
 * Created on 2023-04-13 11:25
 *
 * @author 颜洪毅
 * @version 1.0.0
 * @since 1.0.0
 */
public class GridFilterDialog extends BaseDialog {
    private LinearLayout filterRoot;

    public GridFilterDialog(@NonNull @NotNull Context context) {
        super(context);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setContentView(R.layout.dialog_grid_filter);
        filterRoot = findViewById(R.id.filterRoot);
    }

    public interface Callback {
        void change();
    }

    public void setOnDismiss(Callback callback) {
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (selectChange) {
                    callback.change();
                }
            }
        });
    }

//    public void setData(MovieSort.SortData sortData) {
//        ArrayList<MovieSort.SortFilter> filters = sortData.filters;
//        for (MovieSort.SortFilter filter : filters) {
//            View line = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_filter, null);
//            ((TextView) line.findViewById(R.id.filterName)).setText(filter.name);
//            TvRecyclerView gridView = line.findViewById(R.id.mFilterKv);
//            gridView.setHasFixedSize(true);
//            gridView.setLayoutManager(new V7LinearLayoutManager(getContext(), 0, false));
//            GridFilterKVAdapter filterKVAdapter = new GridFilterKVAdapter();
//            gridView.setAdapter(filterKVAdapter);
//            String key = filter.key;
//            ArrayList<String> values = new ArrayList<>(filter.values.keySet());
//            ArrayList<String> keys = new ArrayList<>(filter.values.values());
//            filterKVAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                View pre = null;
//
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    selectChange = true;
//                    String filterSelect = sortData.filterSelect.get(key);
//                    if (filterSelect == null || !filterSelect.equals(keys.get(position))) {
//                        sortData.filterSelect.put(key, keys.get(position));
//                        if (pre != null) {
//                            TextView val = pre.findViewById(R.id.filterValue);
//                            val.getPaint().setFakeBoldText(false);
//                            val.setTextColor(getContext().getResources().getColor(R.color.color_FFFFFF));
//                        }
//                        TextView val = view.findViewById(R.id.filterValue);
//                        val.getPaint().setFakeBoldText(true);
//                        // takagen99: Added Theme Color
////                        val.setTextColor(getContext().getResources().getColor(R.color.color_theme));
//                        TypedArray a = getContext().obtainStyledAttributes(R.styleable.themeColor);
//                        int themeColor = a.getColor(R.styleable.themeColor_color_theme, 0);
//                        val.setTextColor(themeColor);
//                        pre = view;
//                    } else {
//                        sortData.filterSelect.remove(key);
//                        TextView val = pre.findViewById(R.id.filterValue);
//                        val.getPaint().setFakeBoldText(false);
//                        val.setTextColor(getContext().getResources().getColor(R.color.color_FFFFFF));
//                        pre = null;
//                    }
//                }
//            });
//            filterKVAdapter.setNewData(values);
//            filterRoot.addView(line);
//        }
//    }

    private boolean selectChange = false;

    public void show() {
        selectChange = false;
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // layoutParams.dimAmount = 0f;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }
}