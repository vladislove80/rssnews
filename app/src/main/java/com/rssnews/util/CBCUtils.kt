package com.rssnews.util

import com.rssnews.data.Category

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */

val general = mutableListOf(
    Category("Top Stories", "https://rss.cbc.ca/lineup/topstories.xml", true),
    Category("World", "https://rss.cbc.ca/lineup/world.xml"),
    Category("Canada", "https://rss.cbc.ca/lineup/canada.xml"),
    Category("Politics", "https://rss.cbc.ca/lineup/politics.xml"),
    Category("Business", "https://rss.cbc.ca/lineup/business.xml"),
    Category("Health", "https://rss.cbc.ca/lineup/health.xml"),
    Category("Arts & Entertainment", "https://rss.cbc.ca/lineup/arts.xml"),
    Category("Technology & Science", "https://rss.cbc.ca/lineup/technology.xml"),
    Category("Offbeat", "https://rss.cbc.ca/lineup/offbeat.xml"),
    Category("Aboriginal", "https://www.cbc.ca/cmlink/rss-cbcaboriginal")
)

val sport = mutableListOf(
    Category("Sports", "https://rss.cbc.ca/lineup/sports.xml", true),
    Category("MLB", "https://rss.cbc.ca/lineup/sports-mlb.xml"),
    Category("NBA", "https://rss.cbc.ca/lineup/sports-nba.xml"),
    Category("Curling", "https://rss.cbc.ca/lineup/sports-curling.xml"),
    Category("CFL", "https://rss.cbc.ca/lineup/sports-cfl.xml"),
    Category("NFL", "https://rss.cbc.ca/lineup/sports-nfl.xml"),
    Category("lNHL", "https://rss.cbc.ca/lineup/sports-nhl.xml"),
    Category("Soccer", "https://rss.cbc.ca/lineup/sports-soccer.xml"),
    Category("Figure Skating", "https://rss.cbc.ca/lineup/sports-figureskating.xml")
)

val regional = mutableListOf(
    Category("British Columbia", "https://rss.cbc.ca/lineup/canada-britishcolumbia.xml", true),
    Category("Kamloops", "https://rss.cbc.ca/lineup/canada-kamloops.xml"),
    Category("Calgary", "https://rss.cbc.ca/lineup/canada-calgary.xml"),
    Category("Edmonton", "https://rss.cbc.ca/lineup/canada-edmonton.xml"),
    Category("Saskatchewan", "https://rss.cbc.ca/lineup/canada-saskatchewan.xml"),
    Category("Saskatoon", "https://rss.cbc.ca/lineup/canada-saskatoon.xml"),
    Category("Manitoba", "https://rss.cbc.ca/lineup/canada-manitoba.xml"),
    Category("Thunder Bay", "https://rss.cbc.ca/lineup/canada-thunderbay.xml"),
    Category("Sudbury", "https://rss.cbc.ca/lineup/canada-sudbury.xml"),
    Category("Windsor", "https://rss.cbc.ca/lineup/canada-windsor.xml"),
    Category("London", "https://www.cbc.ca/cmlink/rss-canada-london"),
    Category("Kitchener-Waterloo", "https://rss.cbc.ca/lineup/canada-kitchenerwaterloo.xml"),
    Category("Toronto", "https://rss.cbc.ca/lineup/canada-toronto.xml"),
    Category("Hamilton", "https://rss.cbc.ca/lineup/canada-hamiltonnews.xml"),
    Category("Montreal", "http://rss.cbc.ca/lineup/canada-montreal.xml"),
    Category("New Brunswick", "https://rss.cbc.ca/lineup/canada-newbrunswick.xml"),
    Category("Prince Edward Island", "https://rss.cbc.ca/lineup/canada-pei.xml"),
    Category("Nova Scotia", "https://rss.cbc.ca/lineup/canada-novascotia.xml"),
    Category("Newfoundland & Labrador", "https://rss.cbc.ca/lineup/canada-newfoundland.xml"),
    Category("North", "https://rss.cbc.ca/lineup/canada-north.xml")
)
