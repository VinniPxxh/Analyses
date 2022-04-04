package enumsandinterfaces;

public class Analyse {
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer obj_txt_an : analyzers) {
            if (obj_txt_an.processText(text) != Label.OK) return obj_txt_an.processText(text);
        }
        return Label.OK;
    }
    public class SpamAnalyzer extends KeywordAnalyzer implements TextAnalyzer {
        private String[] keywords;

        SpamAnalyzer(String[] keywords) {
            this.keywords = keywords;
        }

        @Override
        protected String[] getKeywords() {
            return keywords;
        }

        @Override
        protected Label getLabel() {
            return Label.SPAM;

        }

        @Override
        public Label processText(String my_text) {
             my_text : getKeywords();
             if (my_text.contains(my_text)) return getLabel();

            return Label.OK;

        }
    }

    public class NegativeTextAnalyzer extends KeywordAnalyzer implements TextAnalyzer {
        private String[] keywords_negative = {":(", ":|", "=("};

        @Override
        protected String[] getKeywords() {
            return keywords_negative;
        }

        @Override
        protected Label getLabel() {
            return Label.NEGATIVE_TEXT;
        }
    }

    public class TooLongTextAnalyzer implements TextAnalyzer {

        private int maxLength;
        public TooLongTextAnalyzer( int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public Label processText(String my_text) {
            if(my_text.length() < maxLength) return Label.TOO_LONG;

            return Label.OK;
        }
    }

    public abstract class KeywordAnalyzer implements TextAnalyzer {

        abstract String[] getKeywords();

        protected abstract Label getLabel();

        @Override
        public Label processText(String my_text) {
            my_text : getKeywords();
            if (my_text.contains(my_text)) return getLabel();

            return Label.OK;
        }
    }
}
