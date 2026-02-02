## Сравнение BCORE-12 vs BCORE-13
Критерий	BCORE-12 (println)	BCORE-13 (JTE)
Строк Java кода	~50	~5
Смешивание Java/HTML	Да (нечитаемо)	Нет (разделены)
IDE подсветка HTML	Нет	Да (в .jte файлах)
XSS защита	Ручная (легко забыть)	Автоматическая
Type-safety	Нет	Да (compile error если параметр не передан)