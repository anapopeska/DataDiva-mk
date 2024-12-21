package com.example.project1.web;

import com.example.project1.model.CompanyModel;
import com.example.project1.model.CompanyHistoryPriceModel;
import com.example.project1.repository.CompanyHistoryPriceRepository;
import com.example.project1.service.SearchService;
import com.example.project1.service.LSTMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final LSTMService LSTMService;
    private final CompanyHistoryPriceRepository companyHistoryPriceRepository;
    @GetMapping("/")
    public String getLogPage(Model model) {
        return "log";
    }

    @GetMapping("/index")
    public String getIndexPage(Model model) {
        model.addAttribute("companies", searchService.findAll());
        return "index";
    }

    @GetMapping("/today")
    public String getTodayCompanyPage(Model model) {
        model.addAttribute("prices", searchService.findAllToday());
        return "today";
    }

    @GetMapping("/view")
    public String showViewPage(@RequestParam(required = false) String companyCode, Model model) {
        if (companyCode != null && !companyCode.isEmpty()) {
            // Повик од репозиториумот за наоѓање на историја по companyCode
            List<CompanyHistoryPriceModel> historyData = companyHistoryPriceRepository.findByCompanyCompanyCode(companyCode);
            model.addAttribute("historyData", historyData);
        }

        // Пренесување на список со компании за селектирање во view
        model.addAttribute("companies", searchService.findAll()); // Ако имате метод findAll во SearchService
        return "view";
    }

    // @GetMapping("visualize")
    // public String getCompanyPage(@RequestParam(name = "companyId") Long companyId, Model model) throws Exception {
    //     List<Map<String, Object>> companyData = new ArrayList<>();
    //     CompanyModel company = searchService.findById(companyId);

    //     Map<String, Object> data = new HashMap<>();
    //     data.put("companyCode", company.getCompanyCode());
    //     data.put("lastUpdated", company.getLastUpdated());

    //     List<LocalDate> dates = new ArrayList<>();
    //     List<Double> prices = new ArrayList<>();

    //     for (CompanyHistoryPriceModel historicalData : company.getHistoricalData()) {
    //         dates.add(historicalData.getDate());
    //         prices.add(historicalData.getLastTransactionPrice());
    //     }

    //     data.put("dates", dates);
    //     data.put("prices", prices);
    //     data.put("id", company.getId());
    //     companyData.add(data);

    //     model.addAttribute("companyData", companyData);
    //     return "visualize";
    // }
    @GetMapping("/company")
    public String getCompanyPage(@RequestParam(name = "companyId") Long companyId, Model model) throws Exception {
        List<Map<String, Object>> companyData = new ArrayList<>();
        CompanyModel company = searchService.findById(companyId);

        Map<String, Object> data = new HashMap<>();
        data.put("companyCode", company.getCompanyCode());
        data.put("lastUpdated", company.getLastUpdated());

        List<LocalDate> dates = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        for (CompanyHistoryPriceModel historicalData : company.getHistoricalData()) {
            dates.add(historicalData.getDate());
            prices.add(historicalData.getLastTransactionPrice());
        }

        data.put("dates", dates);
        data.put("prices", prices);
        data.put("id", company.getId());
        companyData.add(data);

        model.addAttribute("companyData", companyData);
        return "company";
    }

}
