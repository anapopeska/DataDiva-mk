package com.example.project1.data.pipeline.impl;

import com.example.project1.data.Transformer;
import com.example.project1.data.pipeline.Filter;
import com.example.project1.model.CompanyModel;
import com.example.project1.model.CompanyHistoryPriceModel;
import com.example.project1.repository.CompanyModelRepository;
import com.example.project1.repository.CompanyHistoryPriceRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class F2 implements Filter<List<CompanyModel>> {

    private final CompanyModelRepository companyModelRepository;
    private final CompanyHistoryPriceRepository companyHistoryPriceRepository;

    private static final String HISTORICAL_DATA_URL = "https://www.mse.mk/mk/stats/symbolhistory/";

    public F2(CompanyModelRepository companyModelRepository, CompanyHistoryPriceRepository companyHistoryPriceRepository) {
        this.companyModelRepository = companyModelRepository;
        this.companyHistoryPriceRepository = companyHistoryPriceRepository;
    }

    public List<CompanyModel> execute(List<CompanyModel> input) throws IOException {
        List<CompanyModel> companies = new ArrayList<>();

        for (CompanyModel company : input) {
            if (company.getLastUpdated() == null) {
                for (int i = 1; i <= 10; i++) {
                    int temp = i - 1;
                    LocalDate fromDate = LocalDate.now().minusYears(i);
                    LocalDate toDate = LocalDate.now().minusYears(temp);
                    addHistoricalData(company, fromDate, toDate);
                }
            } else {
                companies.add(company);
            }
        }

        return companies;
    }

    private void addHistoricalData(CompanyModel company, LocalDate fromDate, LocalDate toDate) throws IOException {
        Connection.Response response = Jsoup.connect(HISTORICAL_DATA_URL + company.getCompanyCode())
                .data("FromDate", fromDate.toString())
                .data("ToDate", toDate.toString())
                .method(Connection.Method.POST)
                .execute();

        Document document = response.parse();

        Element table = document.select("table#resultsTable").first();

        if (table != null) {
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");

                if (columns.size() > 0) {
                    LocalDate date = Transformer.parseDate(columns.get(0).text(), "d.M.yyyy");

                    if (companyHistoryPriceRepository.findByDateAndCompany(date, company).isEmpty()) {


                        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);

                        Double lastTransactionPrice = Transformer.parseDouble(columns.get(1).text(), format);
                        Double maxPrice = Transformer.parseDouble(columns.get(2).text(), format);
                        Double minPrice = Transformer.parseDouble(columns.get(3).text(), format);
                        Double averagePrice = Transformer.parseDouble(columns.get(4).text(), format);
                        Double percentageChange = Transformer.parseDouble(columns.get(5).text(), format);
                        Integer quantity = Transformer.parseInteger(columns.get(6).text(), format);
                        Integer turnoverBest = Transformer.parseInteger(columns.get(7).text(), format);
                        Integer totalTurnover = Transformer.parseInteger(columns.get(8).text(), format);

                        if (maxPrice != null) {

                            if (company.getLastUpdated() == null || company.getLastUpdated().isBefore(date)) {
                                company.setLastUpdated(date);
                            }

                            CompanyHistoryPriceModel companyHistoryPriceModel = new CompanyHistoryPriceModel(
                                    date, lastTransactionPrice, maxPrice, minPrice, averagePrice, percentageChange,
                                    quantity, turnoverBest, totalTurnover);
                            companyHistoryPriceModel.setCompany(company);
                            companyHistoryPriceRepository.save(companyHistoryPriceModel);
                            company.getHistoricalData().add(companyHistoryPriceModel);
                        }
                    }
                }
            }
        }

        companyModelRepository.save(company);
    }

}
