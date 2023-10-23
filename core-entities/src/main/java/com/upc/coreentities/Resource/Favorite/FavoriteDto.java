package com.upc.coreentities.Resource.Favorite;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {
    private Long id;
    private Long userProfileId;
    private Long businessProfileId;

}
