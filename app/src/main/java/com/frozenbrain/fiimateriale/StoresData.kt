package com.frozenbrain.fiimateriale

object StoresData {
    private var stores: MutableList<String>? = null
    fun getStores(): List<String>? {
        return stores
    }

    fun filterData(searchString: String?): List<String> {
        var searchString = searchString
        val searchResults: MutableList<String> = ArrayList()
        if (searchString != null) {
            searchString = searchString.toLowerCase()
            for (rec in stores!!) {
                if (rec.toLowerCase().contains(searchString)) {
                    searchResults.add(rec)
                }
            }
        }
        return searchResults
    }

    init {
        val stores = ArrayList<String>()
        stores.add("Amazon")
        stores.add("Sears")
        stores.add("Ebay Home")
        stores.add("Macys Home")
        stores.add("JCpenney Kids")
        stores.add("Ebay Electronics")
        stores.add("Amazon Appliance")
        stores.add("Ebay Mobiles")
        stores.add("Ebay Kids")
        stores.add("Amazon Fashion")
        stores.add("Ebay Travel")
        stores.add("JCpenney Home")
        stores.add("JCpenney Luggage")
        stores.add("JCpenney Appliance")
        stores.add("JCpenney Fashion")
        stores.add("Amazon Luggage")
        stores.add("Macys Jewellery")
        stores.add("JCpenney Jewellery")
        stores.add("Amazon Jewellery")

        this.stores = stores
    }
}