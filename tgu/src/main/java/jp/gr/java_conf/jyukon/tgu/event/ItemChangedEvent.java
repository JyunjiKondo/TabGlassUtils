package jp.gr.java_conf.jyukon.tgu.event;

import jp.gr.java_conf.jyukon.tgu.model.Item;
import lombok.Getter;

public class ItemChangedEvent {
    @Getter final Item item;

    public ItemChangedEvent(Item item) {
        this.item = item;
    }
}
