package com.upc.coreentities.Resource.Review;

import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateReview {
    private int score;
    private String comment;
}
