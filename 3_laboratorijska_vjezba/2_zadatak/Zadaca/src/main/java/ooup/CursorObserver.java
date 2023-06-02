package ooup;

import ooup.TextEditorModel.Location;

public interface CursorObserver {
    void updateCursorLocation(Location location);
}
