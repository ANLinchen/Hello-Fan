/*
使用单例设计模式来实现

用于分析makemeahanzi这个项目提供的json，比如以下：
{"character":"亂","strokes":["M 352 793 Q 310 751 212 698 Q 208 691 213 688 Q 241 688 362 742 Q 386 758 441 774 Q 454 775 458 782 Q 461 792 450 800 Q 398 833 368 828 Q 362 824 363 815 Q 363 802 352 793 Z","M 170 607 Q 186 591 202 570 Q 212 560 224 559 Q 233 559 237 569 Q 241 579 237 603 Q 231 624 169 644 Q 157 648 152 646 Q 148 645 148 634 Q 151 625 170 607 Z","M 288 662 Q 330 605 345 603 Q 355 602 361 615 Q 364 625 358 641 Q 342 671 294 682 Q 287 683 285 675 Q 284 669 288 662 Z","M 452 702 Q 436 675 403 634 Q 399 628 403 623 Q 409 622 415 626 Q 476 671 508 687 Q 521 691 518 700 Q 514 712 498 725 Q 483 738 466 740 Q 456 740 456 725 Q 457 715 452 702 Z","M 361 466 Q 431 520 465 535 Q 474 536 474 545 Q 475 555 453 566 Q 399 602 378 588 Q 317 558 214 529 Q 196 526 211 512 Q 226 500 255 508 Q 342 554 376 550 Q 389 550 391 541 Q 391 535 348 478 C 330 454 337 448 361 466 Z","M 368 443 Q 367 453 361 466 C 358 473 358 473 348 478 Q 318 493 297 496 Q 290 497 290 488 Q 289 482 295 476 Q 320 446 336 435 C 360 417 372 413 368 443 Z","M 199 406 Q 181 422 158 429 Q 151 432 145 424 Q 141 420 148 409 Q 184 298 147 118 Q 143 103 137 86 Q 122 38 150 4 Q 160 -9 169 4 Q 197 34 198 136 Q 198 301 213 382 C 215 393 215 393 199 406 Z","M 312 403 Q 421 428 444 420 Q 454 416 457 393 Q 470 215 458 77 Q 457 49 439 44 Q 430 41 381 53 Q 371 56 370 49 Q 371 42 385 32 Q 416 4 439 -26 Q 452 -41 467 -40 Q 483 -36 504 11 Q 529 60 525 109 Q 518 227 509 336 Q 506 379 524 414 Q 534 429 522 441 Q 504 456 468 469 Q 447 478 431 467 Q 409 457 369 444 Q 368 444 368 443 L 336 435 Q 236 411 199 406 C 169 401 183 379 213 382 Q 228 382 251 388 Q 267 392 282 395 L 312 403 Z","M 369 328 Q 329 321 288 307 Q 284 304 282 306 Q 281 307 283 311 Q 323 366 332 375 Q 338 382 334 389 Q 330 395 312 403 C 285 416 275 420 282 395 Q 288 379 280 356 Q 270 326 231 295 Q 224 288 226 281 Q 230 271 245 261 Q 252 255 259 262 Q 280 280 377 314 C 405 324 399 333 369 328 Z","M 377 314 Q 396 287 406 285 Q 416 282 422 293 Q 425 302 421 317 Q 412 345 365 365 Q 358 368 356 360 Q 355 354 369 328 L 377 314 Z","M 363 145 Q 373 161 383 183 Q 392 210 412 227 Q 425 237 414 247 Q 401 257 382 262 Q 366 269 348 261 Q 332 254 302 245 Q 268 238 234 234 Q 222 234 220 231 Q 217 224 232 213 Q 250 203 291 215 Q 340 234 346 234 Q 352 234 352 223 Q 343 183 328 159 L 309 132 Q 303 128 298 121 Q 276 99 228 76 Q 218 72 224 68 Q 243 62 308 93 Q 321 100 337 114 L 363 145 Z","M 337 114 Q 391 78 400 75 Q 404 75 408 82 Q 414 91 401 114 Q 395 135 363 145 L 328 159 Q 319 168 251 184 Q 245 185 246 179 Q 247 172 254 167 Q 279 152 309 132 L 337 114 Z","M 600 707 Q 610 682 601 524 Q 573 113 638 58 Q 669 34 708 27 Q 744 18 790 18 Q 865 15 904 26 Q 947 38 965 54 Q 981 67 965 97 Q 947 149 942 215 Q 942 225 935 235 Q 925 242 919 218 Q 909 193 895 152 Q 877 89 842 82 Q 805 72 743 75 Q 707 76 689 84 Q 659 97 655 127 Q 634 194 649 429 Q 661 652 688 694 Q 706 739 613 768 Q 610 769 608 769 Q 587 773 579 764 Q 570 754 578 744 Q 593 731 600 707 Z"],"medians":[[[449,786],[415,793],[390,790],[343,754],[216,693]],[[156,638],[212,598],[224,573]],[[296,671],[331,642],[346,615]],[[467,728],[479,701],[408,629]],[[212,521],[258,525],[353,563],[401,567],[422,548],[392,508],[364,484],[366,478]],[[301,486],[361,447]],[[155,418],[178,392],[185,345],[180,186],[160,46],[160,8]],[[204,401],[220,396],[241,399],[450,446],[488,421],[484,333],[493,89],[488,55],[460,11],[431,19],[377,48]],[[291,391],[304,371],[281,326],[271,319],[263,292],[281,291],[351,316],[366,312]],[[363,358],[392,327],[410,297]],[[228,228],[262,222],[348,247],[359,247],[379,234],[357,171],[322,122],[276,88],[228,71]],[[254,176],[371,118],[400,83]],[[588,754],[617,740],[645,702],[622,457],[617,262],[625,154],[636,106],[648,83],[681,60],[743,48],[833,48],[889,63],[918,84],[931,227]]]}
*/

package com.test.util;

import android.graphics.Path;

import com.test.model.GPoint2D;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StrokeParser {

    private StrokeParser() {}

    public static List<Path> parse(String json) {
        List<Path> paths = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray strokes = jsonObject.getJSONArray("strokes");
            for (int i = 0; i < strokes.length(); i++) {
                String stroke = strokes.getString(i);
                Path path = stringToPath(stroke);
                paths.add(path);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return paths;
    }

//    public static List<List<GPoint2D>>

    // 这里的标记参考了：https://www.jianshu.com/p/c819ae16d29b
    // 再根据类型转为代码，参考了：https://developer.android.com/reference/android/graphics/Path
    // !!!注意：C的类型不太确定，根据参数猜想是：cubicTo
    private static Path stringToPath(String stroke) {
        Path path = new Path();
        String[] splited = stroke.split("\\s+");
        int i = 0;
        int x1, y1, x2, y2, x3, y3;
        while (i < splited.length) {
            switch (splited[i]) {
                case "M":
                    x1 = Integer.valueOf(splited[i + 1]);
                    y1 = Integer.valueOf(splited[i + 2]) - 900;
                    path.moveTo(x1, -y1);
                    i = i + 3;
                    break;
                case "L":
                    x1 = Integer.valueOf(splited[i + 1]);
                    y1 = Integer.valueOf(splited[i + 2]) - 900;
                    path.lineTo(x1, -y1);
                    i = i + 3;
                    break;
                case "C":
                    x1 = Integer.valueOf(splited[i + 1]);
                    y1 = Integer.valueOf(splited[i + 2]) - 900;
                    x2 = Integer.valueOf(splited[i + 3]);
                    y2 = Integer.valueOf(splited[i + 4]) - 900;
                    x3 = Integer.valueOf(splited[i + 5]);
                    y3 = Integer.valueOf(splited[i + 6]) - 900;
                    path.cubicTo(x1, -y1, x2, -y2, x3, -y3);
                    i = i + 7;
                    break;
                case "Q":
                    x1 = Integer.valueOf(splited[i + 1]);
                    y1 = Integer.valueOf(splited[i + 2]) - 900;
                    x2 = Integer.valueOf(splited[i + 3]);
                    y2 = Integer.valueOf(splited[i + 4]) - 900;
                    path.quadTo(x1, -y1, x2, -y2);
                    i = i + 5;
                    break;
                case "Z":
                    path.close();
                    i = i + 1;
                    break;
            }
        }
        return path;
    }
}
