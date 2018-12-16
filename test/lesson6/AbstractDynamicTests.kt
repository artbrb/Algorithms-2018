package lesson6

import kotlin.test.assertEquals

abstract class AbstractDynamicTests {
    fun longestCommonSubSequence(longestCommonSubSequence: (String, String) -> String) {
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("1", longestCommonSubSequence("1", "1"))
        assertEquals("13", longestCommonSubSequence("123", "13"))


        assertEquals("уе ", longestCommonSubSequence("я помню чудное мгновенье", "уже забыл"))
        assertEquals("", longestCommonSubSequence("", ""))
        assertEquals("т  е", longestCommonSubSequence("пусть всё будет", "так как ты захочешь"))
        assertEquals("п", longestCommonSubSequence("ппппппппппппппп", "п"))
        assertEquals("хт ры епоое\n" +
                "Над ми ои\n" +
                "аре ен соню\n" +
                "пет пеи\n" +
                "А  ке а сни сате,\n" +
                "т иой от  к кае\n" +
                "апаом.",
                longestCommonSubSequence("Пахнет рыхлыми драченами;\n" +
                "У порога в дежке квас,\n" +
                "Над печурками точеными\n" +
                "Тараканы лезут в паз.\n" +
                "\n" +
                "Вьется сажа над заслонкою,\n" +
                "В печке нитки попелиц,\n" +
                "А на лавке за солонкою -\n" +
                "Шелуха сырых яиц.\n" +
                "\n" +
                "Мать с ухватами не сладится,\n" +
                "Нагибается низко,\n" +
                "Старый кот к махотке крадется\n" +
                "На парное молоко.\n",
                        "Квохчут куры беспокойные\n" +
                "Над оглоблями сохи,\n" +
                "На дворе обедню стройную\n" +
                "Запевают петухи.\n" +
                "\n" +
                "А в окне на сени скатые,\n" +
                "От пугливой шумоты,\n" +
                "Из углов щенки кудлатые\n" +
                "Заползают в хомуты. "))


        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
        assertEquals("e kerwelkkd r", longestCommonSubSequence(
                "oiweijgw kejrhwejelkrw kjhdkfjs hrk",
                "perhkhk lerkerorwetp lkjklvvd durltr"
        ))
        assertEquals(""" дд саы чтых,
евшнео ваа се сви дн.
        """.trimIndent(), longestCommonSubSequence(
                """
Мой дядя самых честных правил,
Когда не в шутку занемог,
Он уважать себя заставил
И лучше выдумать не мог.
                """.trimIndent(),
                """
Так думал молодой повеса,
Летя в пыли на почтовых,
Всевышней волею Зевеса
Наследник всех своих родных.
                """.trimIndent()
        ))
    }

    fun longestIncreasingSubSequence(longestIncreasingSubSequence: (List<Int>) -> List<Int>) {
        assertEquals(listOf(), longestIncreasingSubSequence(listOf()))
        assertEquals(listOf(1), longestIncreasingSubSequence(listOf(1)))
        assertEquals(listOf(1, 2), longestIncreasingSubSequence(listOf(1, 2)))
        assertEquals(listOf(2), longestIncreasingSubSequence(listOf(2, 1)))
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                longestIncreasingSubSequence(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        )
        assertEquals(listOf(2, 8, 9, 12), longestIncreasingSubSequence(listOf(2, 8, 5, 9, 12, 6)))
        assertEquals(listOf(23, 34, 56, 87, 91, 98, 140, 349), longestIncreasingSubSequence(listOf(
                23, 76, 34, 93, 123, 21, 56, 87, 91, 12, 45, 98, 140, 12, 5, 38, 349, 65, 94,
                45, 76, 15, 99, 100, 88, 84, 35, 88
        )))
    }

    fun shortestPathOnField(shortestPathOnField: (String) -> Int) {
        assertEquals(1, shortestPathOnField("input/field_in2.txt"))
        assertEquals(12, shortestPathOnField("input/field_in1.txt"))
        assertEquals(43, shortestPathOnField("input/field_in3.txt"))

        assertEquals(75, shortestPathOnField("input/field_in4.txt"))
        assertEquals(88, shortestPathOnField("input/field_in5.txt"))

    }

}