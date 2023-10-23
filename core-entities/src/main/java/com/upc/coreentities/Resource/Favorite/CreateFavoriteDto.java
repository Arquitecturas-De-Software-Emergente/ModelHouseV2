package com.upc.coreentities.Resource.Favorite;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateFavoriteDto {
    private Long userProfileId;
    private Long businessProfileId;
}
