# For What?
Android Qで追加された「Gesture Navigation」の対応に向けたサンプルアプリ

# 何が入ってる？
以下に対応したサンプルアプリです
- Status BarとNavigation Barの色を変更する（透過する）
- 画面表示を `edge-to-edge` にする
- ListViewの最後に「Navigation Barの分のスペース」を追加する

# Gestural NavigationとDrawerLayoutの競合
Android P以前の場合、DrawerLayoutは画面端からスワイプすると開きます。
ですが、Android Q以降、Gestural Navigationが勝ってしまい、アプリが終了します。

通常は「AndroidXのDrawerLayoutを用いて対処する」となりますが、
私なりの「スワイプの競合」に対する回答は以下です。

> 画面どこでも左から右へスワイプしたらDrawerLayoutが開く。画面端からスワイプしたらアプリが終了する
本ソースコードでは、例としてMainActivityで実装しています。