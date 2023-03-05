export function Embedlink(rowlink) {
  if (rowlink.indexOf("youtube") > 0) {
    return "https://www.youtube.com/embed/" + rowlink.slice(-11);
  } else if (rowlink.indexOf("apple") > 0) {
    return rowlink.slice(0, 8) + "embed." + rowlink.slice(8);
  } else if (rowlink.indexOf("spotify") > 0) {
    return rowlink.slice(0, 25) + "embed/" + rowlink.slice(25);
  } else if (rowlink.indexOf("soundcloud") > 0) {
    return "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/1353341923&color=%23ff5500&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true";
  } else {
    return null;
  }
}
