package com.example.project1.data.pipeline.impl;

import com.example.project1.data.pipeline.Filter;
import com.example.project1.model.CompanyModel;
import com.example.project1.repository.CompanyModelRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class F1 implements Filter<List<CompanyModel>> {

    private final CompanyModelRepository companyModelRepository;

    public F1(CompanyModelRepository companyModelRepository) {
        this.companyModelRepository = companyModelRepository;
    }

    private static final String STOCK_MARKET_URL = "https://www.mse.mk/mk/stats/symbolhistory/kmb";

    @Override
    public List<CompanyModel> execute(List<CompanyModel> input) throws IOException {
        Document document = Jsoup.connect(STOCK_MARKET_URL).get();
        Element selectMenu = document.select("select#Code").first();

        if (selectMenu != null) {
            Elements options = selectMenu.select("option");
            for (Element option : options) {
                String code = option.attr("value");
                if (!code.isEmpty() && code.matches("^[a-zA-Z]+$")) {
                    if (companyModelRepository.findByCompanyCode(code).isEmpty()) {
                        companyModelRepository.save(new CompanyModel(code));
                    }
                }
            }
        }

        return companyModelRepository.findAll();
    }
}
