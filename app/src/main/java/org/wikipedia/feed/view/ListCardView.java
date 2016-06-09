package org.wikipedia.feed.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.wikipedia.R;
import org.wikipedia.feed.model.Card;
import org.wikipedia.views.DefaultRecyclerAdapter;
import org.wikipedia.views.DefaultViewHolder;
import org.wikipedia.views.DrawableItemDecoration;
import org.wikipedia.views.MarginItemDecoration;
import org.wikipedia.views.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class ListCardView<T extends Card> extends CardView {
    @BindView(R.id.view_list_card_header) View headerView;
    @BindView(R.id.view_list_card_footer) View footerView;

    @BindView(R.id.view_list_card_list) RecyclerView recyclerView;

    public ListCardView(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_list_card, this);
        ButterKnife.bind(this);
        initRecycler();
    }

    protected void set(@Nullable RecyclerAdapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    protected void update() {
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    protected void header(@NonNull View view) {
        ViewUtil.replace(headerView, view);
        headerView = view;
    }

    protected void footer(@NonNull View view) {
        ViewUtil.replace(footerView, view);
        footerView = view;
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new MarginItemDecoration(getContext(),
                R.dimen.view_list_card_item_margin));
        recyclerView.addItemDecoration(new DrawableItemDecoration(getContext(),
                R.drawable.divider, true));
    }

    protected abstract static class RecyclerAdapter<T>
            extends DefaultRecyclerAdapter<T, ListCardItemView> {
        protected RecyclerAdapter(@NonNull List<T> items) {
            super(items);
        }

        @Override public DefaultViewHolder<ListCardItemView> onCreateViewHolder(ViewGroup parent,
                                                                                int viewType) {
            return new DefaultViewHolder<>(new ListCardItemView(parent.getContext()));
        }
    }
}