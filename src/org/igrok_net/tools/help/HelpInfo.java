package org.igrok_net.tools.help;

public class HelpInfo {
    private static final String HEADER = "IgRok-NET Tools  Copyright (C) 2023  Oleg Golovchenko\r\n" + //
            "This program comes with ABSOLUTELY NO WARRANTY; see extracted license file.\r\n" + //
            "This is free software, and you are welcome to redistribute it\r\n" + //
            "under certain conditions; see extracted license file.\r\n" + //
            "If license file is not included see <https://www.gnu.org/licenses/gpl-3.0.html#license-text>";

    public HelpInfo() {
        super();
    }

    public void PrintHeader() {
        System.out.println(HEADER);
    }
}
