package jp.gr.java_conf.jyukon.tgu.event;

import jp.gr.java_conf.jyukon.tgu.model.Item;
import lombok.Getter;

public class ItemSelectedEvent {
    @Getter
    final Item item;

    public ItemSelectedEvent(Item item) {
        this.item = item;
    }
}
