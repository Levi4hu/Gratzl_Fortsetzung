package com.example.gratzl.data.model

object SampleData {

    val users = listOf(
        UserProfile(
            id = 1,
            name = "Sophie Huber",
            district = "Wien Leopoldstadt",
            bio = "Hilfsbereite Nachbarin aus dem 2. Bezirk. Ich biete Unterstützung beim Einkaufen, Pflanzengießen und kleine Reparaturen im Haushalt an.",
            rating = 4.9f,
            reviewCount = 12,
            responseTime = "2h",
            responseSpeedLabel = "SEHR SCHNELL",
            skills = listOf(
                UserSkill("Gartenhilfe", "Gießen & Pflege von Zimmer- und Balkonpflanzen."),
                UserSkill("Einkäufe", "Wöchentliche Besorgungen und schwere Taschen."),
                UserSkill("Reparaturen", "Kleinere handwerkliche Hilfe im Haushalt."),
                UserSkill("Pet-Sitting", "Hunde ausführen oder Katzen füttern.")
            )
        ),
        UserProfile(
            id = 2,
            name = "Markus Weber",
            district = "Wien Leopoldstadt",
            bio = "Ich helfe gerne meinen Nachbarn im 2. Bezirk bei alltäglichen Aufgaben. Zuverlässigkeit und ein freundlicher Umgang sind mir wichtig.",
            rating = 4.9f,
            reviewCount = 12,
            responseTime = "< 1h",
            responseSpeedLabel = "SEHR SCHNELL",
            skills = listOf(
                UserSkill("Gartenhilfe"),
                UserSkill("Einkäufe"),
                UserSkill("Reparaturen"),
                UserSkill("Pet-Sitting")
            )
        ),
        UserProfile(
            id = 3,
            name = "Kali K.",
            district = "Donaustadt",
            bio = "Junger Familienvater mit Erfahrung mit Rohren.",
            rating = 4.2f,
            reviewCount = 5,
            skills = listOf(
                UserSkill("Reparaturen", "Kleine Reparaturen im Haushalt."),
                UserSkill("Rohrverlegung")
            )
        ),
        UserProfile(
            id = 4,
            name = "Luca C.",
            district = "Leopoldstadt",
            bio = "Informatik Student mit Erfahrung in Kotlin.",
            rating = 5.0f,
            reviewCount = 8,
            skills = listOf(
                UserSkill("Programmierung"),
                UserSkill("Kotlin"),
                UserSkill("Java")
            )
        )
    )


    var listings = mutableListOf<Listing>(
        Listing(
            id = 1,
            title = "Nachhilfestunden Mathematik",
            description = "Biete Nachhilfe für Schüler bis Klasse 10.",
            isOffer = true,
            category = "Bildung",
            district = "Favoriten",
            priceType = PriceType.PER_HOUR,
            price = 15.0,
            urgency = UrgencyTag.FLEXIBLE,
            userId = 1
        ),
        Listing(
            id = 2,
            title = "Suche Umzugshilfe",
            description = "Brauche 2 Personen für einen Samstag.",
            isOffer = false,
            category = "Haushalt",
            district = "Mariahilf",
            priceType = PriceType.TRADE,
            urgency = UrgencyTag.TODAY,
            userId = 2
        ),
        Listing(
            id = 3,
            title = "Biete Rohrreperatur",
            description = "Schnelle Hilfe bei defekten Rohren.",
            isOffer = true,
            category = "Handwerk",
            district = "Donaustadt",
            priceType = PriceType.FIXED,
            price = 100.0,
            urgency = UrgencyTag.FLEXIBLE,
            userId = 3
        ),
        Listing(
            id = 4,
            title = "Biete Programmierhilfe",
            description = "Durch mein Uni Erfahrung in Programmieren kann ich einiges.",
            isOffer = true,
            category = "Bildung",
            district = "Leopoldstadt",
            priceType = PriceType.COFFEE,
            urgency = UrgencyTag.FLEXIBLE,
            userId = 4
        )
    )

    val chats = listOf(
        Chat(
            id = 1,
            listingId = 1,
            partnerId = 2,
            messages = listOf(
                ChatMessage(1, 1, 2, "Hallo, ist der Platz noch frei?", "14:30"),
                ChatMessage(2, 1, 1, "Ja! Wann passt dir?", "14:32"),
                ChatMessage(3, 1, 2, "Freitag um 17 Uhr?", "14:33"),
            )
        ),
    )

    fun getUserById(id: Int) = users.find { it.id == id }
    fun getListingById(id: Int) = listings.find { it.id == id }
    fun getChatById(id: Int) = chats.find { it.id == id }
    fun getChatForListing(listingId: Int) = chats.find { it.listingId == listingId }
}