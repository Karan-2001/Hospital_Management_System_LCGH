package com.codepalace.chatbot.utils

import com.codepalace.chatbot.utils.Constants.OPEN_GOOGLE
import com.codepalace.chatbot.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val rand = (0..10).random()
        val ran = (0..4).random()
        val message =_message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello")||message.contains("hi")||message.contains("hey") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Hope you are keeping well today!"
                    2 -> "What's up?"
                    else -> "error" }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm doing great!Hope you are keeping well too!"
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }
            message.contains("great",) -> {
                when (random) {
                    0 -> "amazing"
                    1 -> "great"
                    2 -> "nice"
                    else -> "error"
                }
            }
            message.contains("I'm not doing good")||message.contains("I'm not doing well")||message.contains("I'm not doing ok")||message.contains("don't feel ok")||message.contains("don't feel well")||message.contains("don't feel good")||message.contains("do not feel ok") ||message.contains("do not feel well") ||message.contains("do not feel good") ||message.contains(" not feeling good")||message.contains(" not feeling well")||message.contains(" not feeling ok") -> {
                when (ran) {
                    0 -> "Why? What Happened?"
                    1 -> "Wanna talk about it?"
                    2 -> "I'm hear to listen..and give you a shoulder"
                    3->"Can you Please list the symptoms you are feeling?"
                    4->"I'm sorry to hear that?I'm hear to listen to you"
                    else -> "error"
                }
            }
            message.contains("book an appointment") -> {
                when (random) {
                    0 -> "You can Click on the navagation drawer and choose book an appointment"
                    1 -> "you can book an appointment from the home screen"
                    2 -> "You can Book an appontment by choosing the doctor of your choice from the list of doctors at lcgh"
                    else -> "error"
                }
            }
            message.contains("doctors available")||message.contains("available doctors") -> {
                when (random) {
                    0 -> "We have best in class doctors please check out the doctors in the navigation menu"
                    1 -> "Please check out the doctors in the navigation menu"
                    2 -> "LCGH hosts the best in class doctors please go to the doctors menu in the navigation or home menu and book an appointment with an appropriate doctor to take care of your need"
                    else -> "error"
                }
            }
            message.contains("book vaccine") -> {
                when (random) {
                    0 -> "You can book a vaccine by clicking on the navigation drawer and choosing the vaccines option"
                    1 -> "You can book a vaccine by clicking on the navigation drawer and choosing the vaccines option"
                    2 -> "You can book a vaccine by clicking on the navigation drawer and choosing the vaccines option"
                    else -> "error"
                }
            }
            message.contains("Directions to lions cancer hospital",) -> {
                when (random) {
                    0 -> "You can go to the contact us option in the navigation drawer and you will find the directions"
                    1 -> "You can go to the contact us option in the navigation drawer and you will find the directions"
                    2 -> "You can call us at the number given on the contact us option in the navigation drawer and we will guide you..."
                    else -> "error"
                }
            }

            message.contains("cold")||message.contains("cough")||message.contains("headache")||message.contains("fever")||message.contains("body pains")||message.contains("body aches")||message.contains("stomach ache")||message.contains("ache")||message.contains("motions")||message.contains("vomitings")-> {
                when (random) {
                    0 -> "You might be feeling so because of a viral or bacterial cause,please book an appointment with one of our general medicine doctors"
                    1 -> "please book an appointment with one of our general medicine doctors as soon as possible"
                    2 -> "I'm sorry you are feeling sick,please book an appointment with one of our general medicine doctors to rule out the sickness"
                    else -> "error"
                }
            }
            message.contains("bad",)||message.contains("sad",)||message.contains("not good",)||message.contains("depressed",)||message.contains("joke",)  -> {
                when (rand) {
                    0 -> "Don't feel bad let me tell you a joke,What does a cloud wear under his raincoat?\n Thunderwear"
                    1 -> "What does one volcano say to the other?\nI lava you! and we all love you! :)"
                    2 -> "Don't feel bad let me tell you a joke,What did the beach say to the tide when it came in?\nLong time, no sea.:P"
                    3->"Don't feel bad let me tell you a joke,What kind of tree fits in your hand?\nA palm tree...get it"
                        4->"Don't feel bad let me tell you a joke,What do you call two monkeys sharing an Amazon account?\nnPRIME-mates."
                    5->"Don't feel bad let me tell you a joke,What event do spiders love to attend?\n" +
                            "\nWebbings..hehe"
                    6->"Don't feel bad let me tell you a joke,What’s a snake’s strongest subject in school?\n" +
                            "\nHiss-tory."
                    6->"Don't feel bad let me tell you a joke,Where do cows go for entertainment?\n" +
                            "\nMoo-vies."
                    7->"Don't feel bad let me tell you a joke,Where do cows go for entertainment\nMoo-vies."
                    8->"Don't feel bad let me tell you a joke,What do you call a bear with no teeth?\n" +
                            "\nA gummy bear. :D"
                    9->"What do cows read?\n" +
                            "\nCATTLE-logs."
                    10->"Why kind of bug is in the FBI?\n" +
                            "\nA SPY-der."
                    else -> "error"
                }
            }


            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("google")-> {
                OPEN_SEARCH
            }
            message.contains("diagnosis")||message.contains("diagnosised")||message.contains("suffer")-> {
                OPEN_SEARCH
            }
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}