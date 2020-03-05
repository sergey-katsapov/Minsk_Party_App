package katsapov.minskpartyappjava.party_info_details;

import java.util.ArrayList;

import katsapov.minskpartyappjava.data.entities.partyInfo.Feed;
import katsapov.minskpartyappjava.data.entities.partyInfo.children.Children;

class FeedDataMapper {

    private static ArrayList<Feed> feedList = new ArrayList<>();

    static ArrayList<Feed> getListOfFeed(Feed feed) {
        ArrayList<Children> listOfChildren = feed.getData().getChildren();
        ArrayList<Feed> listOfFeeds = new ArrayList<>();
        for (int i = 0; i < listOfChildren.size(); i++) {
            listOfFeeds.add(feed);
        }
        feedList.clear();
        feedList.addAll(listOfFeeds);
        return feedList;
    }
}