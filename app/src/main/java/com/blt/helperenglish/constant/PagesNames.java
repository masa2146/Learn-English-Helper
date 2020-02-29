package com.blt.helperenglish.constant;

import com.blt.helperenglish.R;
import com.blt.helperenglish.model.adapter.PageMainData;
import com.blt.helperenglish.model.adapter.PagePodcastData;
import com.blt.helperenglish.view.main.MainNewsFragment;
import com.blt.helperenglish.view.main.MainPodcastFragment;

public class PagesNames {
    public static final String API_BASE = " http://10.0.2.2:8080/";
    public static final String TRANSLATE_API_BASE = " http://10.0.2.2:1923/api/translate/";

    public static final String GRAMMAR_ADJECTIVES_WITH_PAGE = "adjectives";
    public static final String GRAMMAR_ADJECTIVES_ALL = "adjectives/all";

    public static final String GRAMMAR_ADVERBS_WITH_PAGE = "adverbs";
    public static final String GRAMMAR_ADVERBS_ALL = "adverbs/all";

    public static final String GRAMMAR_NOUNS_WITH_PAGE = "nouns";
    public static final String GRAMMAR_NOUNS_ALL = "nouns/all";

    public static final String GRAMMAR_PREPOSTIONS_WITH_PAGE = "prepositions";
    public static final String GRAMMAR_PREPOSTIONS_ALL = "prepositions/all";

    public static final String GRAMMAR_PRONOUNS_WITH_PAGE = "pronouns";
    public static final String GRAMMAR_PRONOUNS_ALL = "pronouns/all";

    public static final String GRAMMAR_VERBS_WITH_PAGE = "verbs";
    public static final String GRAMMAR_VERBS_ALL = "verbs/all";

    public static final String NEWS_WITH_PAGE = "news";
    public static final String NEWS_ALL = "news/all";

    public static final String PODCAST_LEVEL1_WITH_PAGE = "level_1";
    public static final String PODCAST_LEVEL1_ALL = "level1/all";

    public static final String PODCAST_LEVEL2_WITH_PAGE = "level_2";
    public static final String PODCAST_LEVEL2_ALL = "level2/all";

    public static final String PODCAST_LEVEL3_WITH_PAGE = "level_3";
    public static final String PODCAST_LEVEL3_ALL = "level3/all";

    public static final String PODCAST_LEVEL_BUSINESS_WITH_PAGE = "level_business";
    public static final String PODCAST_LEVEL_BUSINESS_ALL = "level_business/all";

    public static final String PODCAST_VOA1_WITH_PAGE = "level_voa_level_1";
    public static final String PODCAST_VOA1_ALL = "level_voa_level_1/all";

    public static final String PODCAST_VOA2_WITH_PAGE = "level_voa_level_2";
    public static final String PODCAST_VOA2_ALL = "level_voa_level_2/all";

    public static final PageMainData[] mainPageDataArray = new PageMainData[]{
            new PageMainData(R.drawable.profile_img, "News", "Learn English with news", new MainNewsFragment()),
            new PageMainData(R.drawable.profile_img, "Podcast", "Learn English with podcast", new MainPodcastFragment())
    };


    public static final PagePodcastData[] podcastPageDataArray = new PagePodcastData[]{
            new PagePodcastData(R.drawable.profile_img, "Podcast level 1", "Listen level 1 podcast", PodcastType.PODCAST_LEVEL_1, ResponseType.PODCAST_LEVEL),
            new PagePodcastData(R.drawable.profile_img, "Podcast level 2", "Listen level 2 podcast", PodcastType.PODCAST_LEVEL_2, ResponseType.PODCAST_LEVEL),
            new PagePodcastData(R.drawable.profile_img, "Podcast level 3", "Listen level 3 podcast", PodcastType.PODCAST_LEVEL_3, ResponseType.PODCAST_LEVEL),
            new PagePodcastData(R.drawable.profile_img, "Podcast level business", "Listen level business podcast", PodcastType.PODCAST_LEVEL_BUSINESS, ResponseType.PODCAST_LEVEL),
            new PagePodcastData(R.drawable.profile_img, "Podcast voice of America part 1", "Listen level voice of America podcast", PodcastType.PODCAST_VOA_1, ResponseType.PODCAST_VOA),
            new PagePodcastData(R.drawable.profile_img, "Podcast voice of America part 2", "Listen level voice of America podcast", PodcastType.PODCAST_VOA_2, ResponseType.PODCAST_VOA),
    };


}
