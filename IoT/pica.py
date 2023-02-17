import picamera
from time import sleep

camera = picamera.PiCamera()
camera.start_recording('video.h264')
camera.start_preview()
sleep(5)
camera.stop_preview()
camera.stop_recording()