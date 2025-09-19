package sys.pro;

/** Card rank. */
public enum Rank {
  /** TWO. */
  TWO("Двойка", 2),
  /** THREE. */
  THREE("Тройка", 3),
  /** FOUR. */
  FOUR("Четвёрка", 4),
  /** FIVE. */
  FIVE("Пятёрка", 5),
  /** SIX. */
  SIX("Шестёрка", 6),
  /** SEVEN. */
  SEVEN("Семёрка", 7),
  /** EIGHT. */
  EIGHT("Восьмёрка", 8),
  /** NINE. */
  NINE("Девятка", 9),
  /** TEN. */
  TEN("Десятка", 10),
  /** JACK. */
  JACK("Валет", 10),
  /** QUEEN. */
  QUEEN("Дама", 10),
  /** KING. */
  KING("Король", 10),
  /** ACE. */
  ACE("Туз", 11);

  private final String label;
  private final int defaultPoints;

  private Rank(String label, int defaultPoints) {
    this.label = label;
    this.defaultPoints = defaultPoints;
  }

  /** @return label */
  public String getLabel() {
    return label;
  }

  /** @return defaultPoints */
  public int getDefaultPoints() {
    return defaultPoints;
  }
}
