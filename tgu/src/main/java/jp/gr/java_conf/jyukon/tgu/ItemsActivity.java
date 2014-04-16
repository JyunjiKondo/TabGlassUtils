package jp.gr.java_conf.jyukon.tgu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.Response;
import com.google.android.glass.app.Card;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.gr.java_conf.jyukon.tgu.event.ItemChangedEvent;
import jp.gr.java_conf.jyukon.tgu.event.ItemSelectedEvent;
import jp.gr.java_conf.jyukon.tgu.model.Item;
import jp.gr.java_conf.jyukon.tgu.model.ItemResponse;

public class ItemsActivity extends BaseActivity {
    @InjectView(R.id.card_holder) FrameLayout mHolder;
    @Inject RequestManager mRequestManager;
    private Item mItem;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_activity);
        ButterKnife.inject(this);

        if ("item".equals(getIntent().getData().getHost()))
            getItem(Integer.parseInt(getIntent().getData().getPathSegments().get(0)));
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_DPAD_CENTER) {
            if (mItem != null) {
                bus.post(new ItemSelectedEvent(mItem));
                return true;
            }
        } else if (keycode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    private void getItem(int itemId) {
        mRequestManager.doRequest().getItem(itemId,
                new Response.Listener<ItemResponse>() {
                    @Override
                    public void onResponse(ItemResponse response) {
                        bus.post(new ItemChangedEvent(response.getItem()));
                    }
                });
    }

    @Subscribe
    public void onItemChanged(ItemChangedEvent event) {
        mItem = event.getItem();
        insertCard();
    }

    private void insertCard() {
        final Card card = new Card(this);
        card.setText(mItem.getTitle());
        if (mItem.getPlaces().get(0).getName() != null)
            card.setFootnote(mItem.getPlaces().get(0).getName());
        card.setImageLayout(Card.ImageLayout.FULL);
        addImage(card, mItem.getImageUrls().get(0).get("normal_L"));
    }

    private void addImage(final Card card, final String imageUrl) {
        mRequestManager.doRequest().getImage(imageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        card.addImage(bitmap);
                        addView(card.getView());
                    }
                });
    }

    private void addView(View view) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mHolder.addView(view, params);
    }
}