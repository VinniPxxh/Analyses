package enumsandinterfaces;

public class Analyse {
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
            String[] words_spam = getKeywords();
            for (String key_i : words_spam) {
                boolean check = my_text.contains(key_i);
                if (check) return getLabel();
            }
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

    public class TooLongTextAnalyzer {
        private String[] keywords;

        private int maxLength;

        TooLongTextAnalyzer(int maxLength) {
            this.maxLength = maxLength;
        }

        public Label processText(String text) {
            if (text.length() > maxLength) return Label.TOO_LONG;
            return Label.OK;
        }
    }

    public abstract class KeywordAnalyzer implements TextAnalyzer {

        abstract String[] getKeywords();

        abstract Label getLabel();

        @Override
        public Label processText(String my_text) {
            for (String key_i : getKeywords()) {
                if (my_text.contains(key_i)) return getLabel();
            }
            return Label.OK;
        }

        public Label checkLabels(TextAnalyzer[] analyzers, String text) {
            for (TextAnalyzer obj_txt_an : analyzers) {
                if (obj_txt_an.processText(text) != Label.OK) return obj_txt_an.processText(text);
            }
            return Label.OK;
        }
    }
}
