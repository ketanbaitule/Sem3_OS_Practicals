/**
 * Study and implementation of SCAN disk scheduling algorithm
 */

class SCAN {
    public static void main(String[] args) {
        int no_of_tracks = 200;
        int request_queues[] = {82, 170, 43, 140, 24, 16, 190};
        int curr_position = 50;

        // For Forward Direction
        boolean goBackword = false;
        int lastTrackBackword = curr_position;
        int lastTrackForward = curr_position;
        for(int track: request_queues){
            if(track < curr_position){
                goBackword = true;
                lastTrackForward = no_of_tracks - 1;
                if(track < lastTrackBackword){
                    lastTrackBackword=track;
                }
            }
            if(!goBackword){
                if(lastTrackForward < track)
                    lastTrackForward = track;
            }
        }

        int no_of_track_movement = lastTrackForward - curr_position;
        if(goBackword)
            no_of_track_movement += lastTrackForward - lastTrackBackword;

        System.out.println("No of Track Movement: "+no_of_track_movement);
    }    
}
