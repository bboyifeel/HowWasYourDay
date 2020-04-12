package com.lu.uni.igorzfeel.howwasyourday.adapters

import com.lu.uni.igorzfeel.howwasyourday.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class UserItem : Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.user_item
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

}