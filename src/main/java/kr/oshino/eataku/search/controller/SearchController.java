package kr.oshino.eataku.search.controller;

import kr.oshino.eataku.search.model.dto.SearchResultDTO;
import kr.oshino.eataku.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    private final SearchService searchService;
    // 피이지 최초 접속
    @GetMapping("/card")
    public String card(@RequestParam(value = "query", defaultValue = "") String keyword,@RequestParam(defaultValue = "0")int page, Model model) {

        log.info("🚀🚀 [ SearchController ] keyword : {} 🚀🚀", keyword);

        List<SearchResultDTO> restaurantLists = new ArrayList<>();

        int size = 30;
        if (keyword != null && !keyword.isEmpty()) {
            restaurantLists = searchService.selectQueryByKeyword(keyword, page, size);
            log.info("🚀🚀 [ SearchController ] restaurantLists[0] : {} 🚀🚀", restaurantLists.get(0));
        }

        model.addAttribute("restaurantLists", restaurantLists);

        return "search/cardSearchPage";
    }
    // 페이지 검색
    @PostMapping("/card")
    @ResponseBody
    public List<SearchResultDTO> cardPage(@RequestParam(defaultValue = "") String keyword,@RequestParam(defaultValue = "0")int page, @RequestParam(required = false) List<String> category ) {

        log.info("🚀🚀 [ SearchController ] keyword : {} page : {} 🚀🚀", keyword, page);

        List<SearchResultDTO> restaurantLists = new ArrayList<>();

        // 카테고리
        int size = 30;
        if (keyword != null && !keyword.isEmpty()) {
//             restaurantLists = searchService.selectQueryByKeyword(keyword, page, size);
             restaurantLists = searchService.selectQueryByKeywords(keyword, page, size, category);
            if (!restaurantLists.isEmpty()) {
                log.info("🚀🚀 [ SearchController ] restaurantLists[0] : {} 🚀🚀", restaurantLists.get(0));
            }
        }

        return restaurantLists;
    }

    @GetMapping("/map")
    public String map() {
        return "search/mapSearchPage";
    }

    @GetMapping("/map/coordinate")
    @ResponseBody
    public List<SearchResultDTO> mapSearch(
            @RequestParam(value = "latitude", defaultValue = "0") Double latitude
            , @RequestParam(value = "longitude", defaultValue = "0") Double longitude
            , @RequestParam("query") String keyword) {

        log.info("🚀🚀 [ SearchController ] latitude : {} longitude : {} 🚀🚀", latitude, longitude);
        List<SearchResultDTO> restaurantLists = new ArrayList<>();

        if (latitude != 0 && longitude != 0) {
            restaurantLists = searchService.selectQueryBylatitudeAndlongitude(latitude, longitude, keyword);
            if (!restaurantLists.isEmpty()) {
                log.info("🚀🚀 [ SearchController ] restaurantLists[0] : {} 🚀🚀", restaurantLists.get(0));
            }
        }

        return restaurantLists;
    }
}
