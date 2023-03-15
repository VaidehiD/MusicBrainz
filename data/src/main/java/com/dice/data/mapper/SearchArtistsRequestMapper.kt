package com.dice.data.mapper

import com.dice.data.entity.SearchTypeRequest

class SearchArtistsRequestMapper {

    fun searchTypeRequest(searchKeyword: String, searchTypeRequest: SearchTypeRequest) =
        "${searchTypeRequest.type}:$searchKeyword"
}