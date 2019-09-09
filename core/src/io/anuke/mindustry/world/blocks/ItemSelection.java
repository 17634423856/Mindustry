package io.anuke.mindustry.world.blocks;

import io.anuke.arc.collection.*;
import io.anuke.arc.function.*;
import io.anuke.arc.scene.style.*;
import io.anuke.arc.scene.ui.*;
import io.anuke.arc.scene.ui.layout.*;
import io.anuke.mindustry.gen.*;
import io.anuke.mindustry.type.*;
import io.anuke.mindustry.ui.Style;

import static io.anuke.mindustry.Vars.*;

public class ItemSelection{

    public static void buildItemTable(Table table, Supplier<Item> holder, Consumer<Item> consumer){

        Array<Item> items = content.items();

        ButtonGroup<ImageButton> group = new ButtonGroup<>();
        group.setMinCheckCount(0);
        Table cont = new Table();
        cont.defaults().size(38);

        int i = 0;

        for(Item item : items){
            if(!data.isUnlocked(item) && world.isZone()) continue;

            ImageButton button = cont.addImageButton(Tex.whiteui, Style.clearToggleTransIbutton, 24, () -> control.input.frag.config.hideConfig()).group(group).get();
            button.changed(() -> consumer.accept(button.isChecked() ? item : null));
            button.getStyle().imageUp = new TextureRegionDrawable(item.icon(Item.Icon.medium));
            button.update(() -> button.setChecked(holder.get() == item));

            if(i++ % 4 == 3){
                cont.row();
            }
        }

        table.add(cont);
    }
}
