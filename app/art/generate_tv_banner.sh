#!/usr/bin/env sh
set -eu

script_dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
app_dir=$(CDPATH= cd -- "$script_dir/.." && pwd)
output_dir="$app_dir/src/main/res/drawable-xhdpi"
icon_source="$app_dir/src/main/res/mipmap-xxxhdpi/ic_launcher.webp"
output="$output_dir/tv_banner.png"
temporary_icon=$(mktemp "${TMPDIR:-/tmp}/torrent-search-tv-icon.XXXXXX.png")
trap 'rm -f "$temporary_icon"' EXIT HUP INT TERM

mkdir -p "$output_dir"

convert "$icon_source" -resize 96x96 "$temporary_icon"
convert -size 320x180 gradient:'#050505-#202020' \
    -fill '#151515' -draw 'polygon 0,138 82,84 146,127 216,82 320,146 320,180 0,180' \
    "$temporary_icon" -geometry +18+42 -composite \
    -font DejaVu-Sans-Bold -pointsize 24 -fill '#F5F5F5' \
    -draw "text 126,94 'Torrent Search'" \
    -depth 8 -strip "$output"

identify "$output"
