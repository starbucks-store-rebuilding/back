package com.starbucks.back.wishlist.application;

import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistListDto;

import java.util.List;

public interface WishlistService {

    List<ResponseReadWishlistListDto> getWishlistListByUserUuid(String userUuid);
}
