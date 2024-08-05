package com.example.citysearchapp.data

class TrieNode {
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
    val cities: MutableList<City> = mutableListOf()
}

class Trie {
    private val root = TrieNode()

    fun insert(city: City) {
        var current = root
        city.name.toLowerCase().forEach { char ->
            current = current.children.computeIfAbsent(char) { TrieNode() }
        }
        current.cities.add(city)
    }

    fun search(prefix: String): List<City> {
        var current = root
        prefix.toLowerCase().forEach { char ->
            current = current.children[char] ?: return emptyList()
        }
        return current.cities
    }
}