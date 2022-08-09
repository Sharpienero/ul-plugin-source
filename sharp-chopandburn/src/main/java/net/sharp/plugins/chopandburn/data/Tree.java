package net.sharp.plugins.chopandburn.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public enum Tree
{
//    REGULAR(1, "Tree"),
    OAK(15, "Oak"),
//    WILLOW(30, "Willow"),
    TEAK(35, "Teak");
//    MAPLE(45, "Maple Tree"),
//    MAHOGANY(50, "Mahogany"),
//    YEW(60, "Yew"),
//    MAGIC(75, "Magic tree");

    private final int level;
    private final String name;
}
