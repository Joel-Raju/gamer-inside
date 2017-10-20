package com.raju.joel.gamerinside.navigation;

import com.raju.joel.gamerinside.R;

/**
 * Created by Joel on 02-Sep-17.
 */

public class NavigationModel {

    public enum NavigationItemEnum {

        NEWS(R.id.menu_item_news, R.string.menu_item_news, R.drawable.ic_chrome_reader_mode_black_24dp),
        DISCOVER(R.id.menu_item_discover, R.string.menu_item_discover_games, R.drawable.ic_view_carousel_black_24dp),
        SEARCH(R.id.menu_item_search, R.string.menu_item_search, R.drawable.ic_search_black_24dp),
        INVALID(12, 0, 0);

        private int id;

        private int titleResource;

        private int iconResource;

        private boolean finishCurrentActivity;

        NavigationItemEnum(int id, int titleResource, int iconResource) {
            this(id, titleResource, iconResource, false);
        }

        NavigationItemEnum(int id, int titleResource, int iconResource, boolean finishCurrentActivity) {
            this.id = id;
            this.titleResource = titleResource;
            this.iconResource = iconResource;
            this.finishCurrentActivity = finishCurrentActivity;

        }

        public static NavigationItemEnum getById(int id) {
            for (NavigationItemEnum value: NavigationItemEnum.values()) {
                if (value.getId() == id) {
                    return value;
                }
            }
            return INVALID;
        }

        public int getId() {
            return id;
        }

        public int getTitleResource() {
            return titleResource;
        }

        public int getIconResource() {
            return iconResource;
        }

        public boolean isFinishCurrentActivity() {
            return finishCurrentActivity;
        }
    }
}
