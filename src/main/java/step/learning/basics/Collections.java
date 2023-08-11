package step.learning.basics;

import java.util.*;

public class Collections {
    public void demo() {
        mapDemo();
    }

    private void mapDemo() {
        Map<String, String> headers;
        // headers = new LinkedHashMap<>();    // Зі збереженням порядку додавання
        headers = new HashMap<>();             // Без збереження порядку додавання
        headers.put("Content-Type", "text/html");
        headers.put("Content-Length", "100500");
        headers.put("Connection", "close");
        headers.put("Host", "localhost");
        headers.put("Authorization", "basic admin:password");
        for(String key: headers.keySet()) {
            System.out.println(String.format(" %s: %s", key, headers.get(key)));
        }
    }

    private void listDemo() {
        // List - interface колекцій-лістов, ArrayList | LinkedList | etc - реалізація
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new LinkedList<String>();

        list2.add("String 1");
        list2.add("String 2");
        list2.add("String 3");
        list2.add(2, "String 2.5");
        for (String str : list2) {
            System.out.println();
        }

        String[] arr = list2.toArray(new String[0]);
        // List<ing> list3; error -- Type argument cannot be of primitive type
        List<Integer> list3; // OK
    }
}

/*
Колекції поділяються на:
- List
- Set
- Map

Особливість Java у тому, що немає повного контролю Generic типів, тобто
колекція сприймається як колекція<Object>
- Немає гарантії перетворення типу (List<String>) json
    [насправді гарантується (List<?>) - що це generic list, але не гарантує якого типу]
- При перетворенні колекції у масив слід зазначати зразок його типу
    [ list2.toArray(new String[0]) ]
- Інтенсивна робота з колекціями не є бажаною, рекомендовано їх використовувати
для прийому даних, після чого перевести дані у масив і вже з ним продовжувати роботу
- ArrayList можна вважати певним компромісом, оскільки в ньому вживається масив,
тобто після отримання всіх даних перетворення не є необхідним, але саме отримання даних
може бути сповільнене, якщо в ньому багато операцій вставляння елементів
(не у кінець масиву), вилучення елементів
 */
