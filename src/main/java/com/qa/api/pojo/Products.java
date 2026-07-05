package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    Integer id;
    String title;
    Double price;
    String description;
    String category;
    String image;
    Rating rating;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rating
    {
        private Double rate;
        private Integer count;
    }




}
