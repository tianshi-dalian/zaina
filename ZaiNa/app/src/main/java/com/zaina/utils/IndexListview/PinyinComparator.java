package com.zaina.utils.IndexListview;


import com.zaina.entity.VFriendsAndtel;

import java.util.Comparator;

/**
 * @author xiaanming
 */
public class PinyinComparator implements Comparator<VFriendsAndtel> {

    public int compare(VFriendsAndtel o1, VFriendsAndtel o2) {
        if (o1.getUserNameLetter().equals("@") || o2.getUserNameLetter().equals("#")) {
            return -1;
        } else if (o1.getUserNameLetter().equals("#") || o2.getUserNameLetter().equals("@")) {
            return 1;
        } else {
            return o1.getUserNameLetter().compareTo(o2.getUserNameLetter());
        }
    }

}
