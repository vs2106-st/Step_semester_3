class AccessRuleEngineP2 {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";

            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";

            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || 
                    "SAME_PACKAGE".equals(accessorContext) || 
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.isEmpty()) {
            return "";
        }

        String[] words = accessorContext.split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                String word = words[i].toLowerCase();
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1));

                if (i < words.length - 1) {
                    sb.append(" ");
                }
            }
        }

        return sb.toString();
    }
}
