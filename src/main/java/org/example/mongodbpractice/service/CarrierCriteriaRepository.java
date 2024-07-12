package org.example.mongodbpractice.service;

import org.example.mongodbpractice.dto.AggregationResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarrierCriteriaRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<AggregationResultDTO> findCarrierCriteria(String searchQuery, String sortByField, String sortOrder, int page, int size, String category) {
//        AggregationOperation carrierSheetLookup = Aggregation.lookup(
//                "carrierSheet",
//                "carrierSheetId",
//                "_id",
//                "carrierSheet"
//        );
        AggregationOperation localLookup = Aggregation.lookup(
                "local",
                "carrierSheetId",
                "id",
                "local"
        );
        AggregationOperation transportLookup = Aggregation.lookup(
                "transport",
                "carrierSheetId",
                "id",
                "transport"
        );
        AggregationOperation freightLookup = Aggregation.lookup(
                "freight",
                "carrierSheetId",
                "id",
                "freight"
        );

        Pageable pageable = PageRequest.of(page, size);
        int skip = pageable.getPageNumber() * pageable.getPageSize();
        AggregationOperation skipStage = Aggregation.skip(skip);
        AggregationOperation limitStage = Aggregation.limit(pageable.getPageSize());

        AggregationOperation unwindLocal = Aggregation.unwind("local"); // Unwind the carrierSheet array
        AggregationOperation unwindTransport = Aggregation.unwind("transport"); // Unwind the transport array
        AggregationOperation unwindFreight = Aggregation.unwind("freight"); // Unwind the transport array

        MatchOperation matchCarrierSheet = Aggregation.match(new Criteria().orOperator(
                Criteria.where("name").is("Carrier"), Criteria.where("name").is("Carrier 02")
        ));
        MatchOperation matchLocal = Aggregation.match(new Criteria().orOperator(
                        Criteria.where("local.name").is("Local"), Criteria.where("local.name").is("Local 2")
        ));

        MatchOperation matchTransport = Aggregation.match(Criteria.where("transport.name").is("Transport"));
        MatchOperation matchFreight = Aggregation.match(Criteria.where("freight.name").is("Frieght"));


        AggregationOperation projectFields = Aggregation.project()
                .and("_id").as("id")
                .and("name").as("carrierName")
                .and("category").as("carrierCategory")
                .and("local.name").as("localName")
                .and("transport.name").as("transportName")
                .and("freight.name").as("freightName");

        /**
         * Here's how you can structure these operations correctly:
         * lookup: Join the collections.
         * unwind: Deconstruct the array created by lookup.
         * match: Filter documents based on a condition.
         * project: Select and rename fields as needed.
         * skip
         * limit
         */

        AggregationOperation groupByUniqueFields = Aggregation.group("id")
                .first("id").as("id")
                .first("carrierName").as("carrierName")
                .first("carrierCategory").as("carrierCategory")
                .first("localName").as("localName")
                .first("transportName").as("transportName")
                .first("freightName").as("freightName");

        Aggregation aggregation = Aggregation.newAggregation(
                localLookup,
                transportLookup,
                freightLookup,
                unwindLocal,
                unwindTransport,
                unwindFreight,
                matchCarrierSheet,
                matchLocal,
                matchFreight,
                matchTransport,
                projectFields,
                groupByUniqueFields
        );

        System.out.println(aggregation.toString());

        AggregationResults<AggregationResultDTO> results = mongoTemplate.aggregate(aggregation, "carrierSheet", AggregationResultDTO.class);

        List<AggregationResultDTO> mappedResults = results.getMappedResults();
//        return new PageImpl<>(mappedResults, pageable, mappedResults.size());
        return mappedResults;
    }
}
