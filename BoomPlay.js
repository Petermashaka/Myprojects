
       let progress = document.getElementById("progress")
       let song = document.getElementById("song")
       let CtrsIcon = document.getElementById("CtrsIcon")

       song.onloadedmetadata=function(){
        progress.max = song.duration;
        progress.value = song.currentTime;
       }
       function playpause(){
        if(CtrsIcon.classList.contains("fa-pause")){
            song.pause();
            CtrsIcon.classList.remove("fa-pause");
            CtrsIcon.classList.add("fa-play");
        }
        else{
            song.play();
            CtrsIcon.classList.add("fa-pause");
            CtrsIcon.classList.remove("fa-play");

        }
       }
if(song.play()){
    setInterval(()=>{
        progress.value = song.currentTime;
    },500)
}
progress.onchange=function(){
    song.play();
    song.currentTime = progress.value;
    CtrsIcon.classList.add("fa-pause");
    CtrsIcon.classList.remove("fa-play");

}
