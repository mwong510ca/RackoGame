package mwong.myprojects.rackocomputerplayers;

/**
 * AbstractHandAnalyzer is the abstract class that has the following variables and methods
 * for HandAnalyzer of Racko game.
 *
 * @author Meisze Wong
 *         www.linkedin.com/pub/macy-wong/46/550/37b/
 */
public abstract class AbstractHandAnalyzer {
    int[] groupHand;
    int[] gapCount;
    int[] discard;
    int[] orgDiscard;
    int[] rangeMax;
    int rating;
    int cardSize;
    int rackSize;
    int cardKey;

    protected AbstractHandAnalyzer(int cardSize, int rackSize, int cardKey) {
        this.cardSize = cardSize;
        this.rackSize = rackSize;
        this.cardKey = cardKey;
    }

    /**
     * Copy the given components to local storage, start analysis it.
     */
    public void analysisNow(byte[] hand, int[] groupHand, int[] gapCount, int[]discardReplacement,
            int[] rangeMax) {
        System.arraycopy(rangeMax, 0, this.rangeMax, 0, rackSize + 2);
        System.arraycopy(gapCount, 0, this.gapCount, 0, rackSize);
        System.arraycopy(discardReplacement, 0, this.discard, 0, cardSize + 1);
        System.arraycopy(groupHand, 0, this.groupHand, 0, rackSize);
        System.arraycopy(discard, 0, discardReplacement, 0, discardReplacement.length);
        analysis(hand);

        rating = 0;
        for (int i = 0; i < rackSize; i++) {
            if (this.gapCount[i] == 0) {
                rating++;
            }
        }
    }

    abstract void analysis(byte[] hand);

    /**
     * Returns the integer array of group hand of each card.
     *
     * @return integer array of group hand of each card
     */
    public int[] getGroupHand() {
        return groupHand.clone();
    }

    /**
     * Returns the integer array of gap count of each slot.
     *
     * @return integer array of gap count of each slot
     */
    public int[] getGapCount() {
        return gapCount.clone();
    }

    /**
     * Returns the integer array of replacement assignment of discard card.
     *
     * @return integer array of replacement assignment of discard card
     */
    public int[] getDiscard() {
        return discard.clone();
    }

    /**
     * Returns the integer array of range maximum of each slot.
     *
     * @return integer array of range maximum of each slot
     */
    public int[] getRangeMax() {
        return rangeMax.clone();
    }

    /**
     * Returns the integer represents the rating of analysis.
     *
     * @return integer represents the rating of analysis
     */
    public int getRating() {
        return rating;
    }
}
