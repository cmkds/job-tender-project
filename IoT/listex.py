import os

from PyQt5 import QtWidgets, QtCore, QtGui
from PyQt5.QtWidgets import *
from PyQt5.uic import loadUi
from PyQt5.QtCore import *
import sys
import cv2
from time import sleep
from PyQt5.QtGui import *

import requests
import xml.etree.ElementTree as ET

#from gpiozero import Button
#from signal import pause
from time import sleep

# from datetime import datetime
# from os import rename
#
#
# from PyQt5.QtGui import QFontDatabase, QFont
#
# import boto3
# from botocore.client import Config

import image_rc
import images_rc

import numpy as np

# 파일 업로드 s3 관련 권한을 가진 IAM계정 정보
ACCESS_KEY_ID = "AKIA2TBRAOMD4EYNB5MS"
ACCESS_SECRET_KEY = "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"
BUCKET_NAME = "team-a502-bucket"


# 사진 촬영 완료 Flag
pictureFin = False

videoFin = False

# def handleUpload(f): # f = 파일명
#     data = open(f, 'rb')
#
#     # '로컬의 해당파일경로'+ 파일명 + 확장자
#     s3 = boto3.resource(
#         's3',
#         aws_access_key_id=ACCESS_KEY_ID,
#         aws_secret_access_key=ACCESS_SECRET_KEY,
#         config=Config(signature_version='s3v4')
#     )
#
#     if ".jpg" in f:
#         s3.Bucket(BUCKET_NAME).put_object(
#             Key=f, Body=data, ContentType='image/jpg')
#     elif ".mp4" in f:
#         s3.Bucket(BUCKET_NAME).put_object(
#             Key=f, Body=data, ContentType='video/mp4')
#
# def renameFile():
#     now = datetime.now()
#
#     # print("photo_" + now.strftime('%Y-%m-%d_%H%M%S%M'))
#     # print("post_" + now.strftime('%Y-%m-%d_%H%M%S%M'))
#     # print("video_" + now.strftime('%Y-%m-%d_%H%M%S%M'))
#
#     photo = "./picture/photo{}.jpg".format(selectPicturePage.selectNum)
#     post = "./final/final.jpg"
#     video = "./video/video.mp4"
#
#     renamePhoto = "./final/photo_" + now.strftime('%Y-%m-%d_%H%M%S%M') + ".jpg"
#     renamePost = "./final/post_" + now.strftime('%Y-%m-%d_%H%M%S%M') + ".jpg"
#     renameVideo = "./final/video_" + now.strftime('%Y-%m-%d_%H:%M:%S:%M') + ".mp4"
#
#     rename(photo, renamePhoto)
#     rename(post, renamePost)
#     rename(video, renameVideo)
#
#     # handleUpload(renamePhoto)
#     # handleUpload(renamePost)
#     # handleUpload(renameVideo)


def findAddress():
    uri = 'http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd'
    service_key = 'LYn67ZcmpVcl8UZTD%2B697IwsAMAkKPidZkk7K2X6ROHRXJwy7HkftIdP6qit4dh5emCC4g8mnW7HFXruqfEYmQ%3D%3D'
    # service_key_decoding = requests.utils.unquote(service_key)
    service_key_decoding = 'LYn67ZcmpVcl8UZTD+697IwsAMAkKPidZkk7K2X6ROHRXJwy7HkftIdP6qit4dh5emCC4g8mnW7HFXruqfEYmQ=='
    print('=============== 도로명 주소 입력=======================')

    seach_se = 'road'

    srchwrd = addressPage.abc.text

    payload = {'ServiceKey': service_key_decoding, 'searchSe': seach_se,
               'srchwrd': srchwrd, 'countPerPage': '100', 'currentPage': '1'}

    resp = requests.get(uri, params=payload)
    root = ET.fromstring(resp.text)

    newAddressListAreaCd = root.findall("newAddressListAreaCd")
    addressPage.abc.newAddressListAreaCd = newAddressListAreaCd

    print('=============== 결과 출력 =======================')



# def finalImageSave():  # bg : jpg     #photo : jpg     #memo : png
#     bgimage = cv2.imread("./bgimage/bg{}.jpg".format(backgroundPage.selectBgNum), cv2.IMREAD_COLOR)
#
#     if selectFramePage.selectNum == 1:  # 가로
#         picture = cv2.imread("./picture/photo{}.jpg".format(selectPicturePage.selectNum), cv2.IMREAD_COLOR)
#         picture = cv2.resize(picture, (700, 350))
#     elif selectFramePage.selectNum == 2:  # 세로
#         picture = cv2.imread("./picture/photo{}.jpg".format(selectPicturePage.selectNum), cv2.IMREAD_COLOR)
#         picture = cv2.resize(picture, (275, 450))
#     # picture = cv2.imread('./picture/photo1.jpg', cv2.IMREAD_COLOR)
#     memo = cv2.imread("./memo/memo1.png", cv2.IMREAD_COLOR)
#     mask = cv2.imread("./picture/mask.png", cv2.IMREAD_GRAYSCALE)
#     image = cv2.imread("./memo/memo1.png", cv2.IMREAD_UNCHANGED)
#
#     bgimage = cv2.resize(bgimage, (750, 500))  # 150 100
#     # picture = cv2.resize(picture, (275, 450))  # 55 90
#     # picture2 = cv2.resize(picture, (700, 350)) # 140 70
#     memo = cv2.resize(memo, (750, 500))  # 55 90 #858 598
#
#     h1, w1 = bgimage.shape[:2]
#     h2, w2 = picture.shape[:2]
#     h3, w3 = memo.shape[:2]
#
#     # mask = cv2.resize(bgimage, (w2, h2))
#
#     # print(h1, w1) #1170 2532
#     # print(h2, w2) #720 426
#     # print(h3, w3) #598 858
#
#     mask = cv2.resize(mask, (w2, h2))
#     image = cv2.resize(image, (w3, h3))
#     mask2 = image[:, :, 3]
#
#     if selectFramePage.selectNum == 1:  # 가로
#         crop = bgimage[20:h2 + 20, 20:w2 + 20]
#     elif selectFramePage.selectNum == 2:  # 세로
#         crop = bgimage[25:h2 + 25, 25:w2 + 25]
#
#     # crop = bgimage[20:h2 + 20, 20:w2 + 20]
#     cv2.copyTo(picture, mask, crop)
#     # cv2.imshow('bgimage', bgimage)
#
#     #cv2.imshow("bgimage", bgimage)
#
#     crop2 = bgimage[0:h3, 0:w3]
#     cv2.copyTo(memo, mask2, crop2)
#
#
#     bgimage = cv2.resize(bgimage, (825, 550))
#     cv2.imwrite("./final/final.jpg".format(), bgimage)  # 사진 저장
#     #cv2.waitKey()
#
#
# class VideoThread(QThread):
#     def __init__(self):
#         super().__init__()
#         self.resolution = [1280, 720] # 카메라 상수 (해상도)
#
#         # 사진 비율_frame1 138 : 70
#         # 사진 비율_frame2 55 : 93
#         self.screenSize = [1011,571]
#         self.videoFlag = True
#
#     # 실시간 촬영 화면 보여주기 (cam.start() 일때 실행)
#     def run(self):
#         global videoFin
#         #frame1 가로 138 , 세로 70 -> 1000 * 508
#         #frame2 가로 55 , 세로 93 -> 414 * 700
#
#         videoPage.videoScreen.resize(self.screenSize[0],self.screenSize[1])
#         videoPage.videoScreen.move(70,170)
#         self.cam = cv2.VideoCapture(0) # 카메라 작동
#
#         self.cam.set(cv2.CAP_PROP_FRAME_WIDTH, self.resolution[0]) # 가로 해상도 설정
#         self.cam.set(cv2.CAP_PROP_FRAME_HEIGHT, self.resolution[1]) # 세로 해상도 설정
#
#         fps = self.cam.get(cv2.CAP_PROP_FPS) # 초당 카메라 영상 프레임 갯수
#
#         if fps == 0.0:
#             fps = 30.0
#
#
#         fourcc = cv2.VideoWriter_fourcc(*'mp4v')
#         out = cv2.VideoWriter('./video/video.mp4', fourcc, fps/4, (self.resolution[0], self.resolution[1]))
#
#         TIME_PER_FRAME = 1 / fps
#
#         while self.videoFlag: #true : 실시간 / false : 정지
#             ret, frame = self.cam.read()
#
#
#             if ret:
#
#                 out.write(frame)
#
#                 frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
#                 # img[세로 , 가로]
#
#
#                 resizeFrame = cv2.resize(frame,
#                                        (self.screenSize[0], self.screenSize[1]),
#                                        interpolation=cv2.INTER_CUBIC)
#
#                 h, w, c = resizeFrame.shape
#                 qFrame = QImage(resizeFrame.data, w, h, w * c, QImage.Format_RGB888)
#
#                 # 자른 사진 화면에 띄우기
#                 streamingPixmap = QPixmap.fromImage(qFrame)
#
#                 videoPage.videoScreen.setPixmap(streamingPixmap)
#
#                 #cv2.imshow("VideoFrame", frame)
#             else:
#                 print("streampicture x")
#
#             sleep(TIME_PER_FRAME)
#
#         out.release()
#         videoFin = True
#         self.cam.release()
#         print("video off")
#
# class CameraThread(QThread):
#     def __init__(self , frameNum):
#         super().__init__()
#         picturePage1.command.connect(self.takePicture)
#         # button.when_pressed = self.takePicture
#         self.frameNum = frameNum
#         self.resolution = [1280, 720] # 카메라 상수 (해상도)
#
#         # 사진 비율_frame1 138 : 70
#         # 사진 비율_frame2 55 : 93
#         self.pictureRatio = [[138,70],[55,93]]
#         self.screenSize = [
#             [948, 590],
#             [311,633]
#         ]
#         self.movePoint = [
#             [80,150],
#             [420,140]
#         ]
#         # self.remoconThread = RemoteControlThread()
#
#     # 실시간 촬영 화면 보여주기 (cam.start() 일때 실행)
#     def run(self):
#
#         #frame1 가로 138 , 세로 70 -> 1000 * 508
#         #frame2 가로 55 , 세로 93 -> 414 * 700
#
#         picturePage1.pictureStreaming.resize(self.screenSize[self.frameNum-1][0],self.screenSize[self.frameNum-1][1])
#         picturePage1.pictureStreaming.move(self.movePoint[self.frameNum - 1][0], self.movePoint[self.frameNum - 1][1])
#         self.cam = cv2.VideoCapture(0) # 카메라 작동
#
#         self.cam.set(cv2.CAP_PROP_FRAME_WIDTH, self.resolution[0]) # 가로 해상도 설정
#         self.cam.set(cv2.CAP_PROP_FRAME_HEIGHT, self.resolution[1]) # 세로 해상도 설정
#
#         fps = self.cam.get(cv2.CAP_PROP_FPS) # 초당 카메라 영상 프레임 갯수
#
#         if fps == 0.0:
#             fps = 30.0
#
#         TIME_PER_FRAME = 1 / fps
#
#         while self.cameraFlag: #true : 실시간 / false : 정지
#             ret, img = self.cam.read()
#
#
#             if ret:
#                 img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
#                 # img[세로 , 가로]
#
#                 # 촬영중인 사진 화면 선택한 프레임에 맞게 자르기
#                 if self.frameNum == 1:
#                     afterHeight = (self.resolution[0] * self.pictureRatio[self.frameNum - 1][1]) // self.pictureRatio[self.frameNum - 1][0]
#                     temp = (self.resolution[1] - afterHeight) // 2
#                     img2 = img[temp:(self.resolution[1] -temp), :]
#                 elif self.frameNum == 2:
#                     afterWidth = (self.resolution[1] * self.pictureRatio[self.frameNum - 1][0]) // self.pictureRatio[self.frameNum-1][1]
#                     temp = (self.resolution[0] - afterWidth) // 2
#                     img2 = img[:, temp:(self.resolution[0] - temp)]
#
#
#                 resizeImg = cv2.resize(img2, (self.screenSize[self.frameNum-1][0], self.screenSize[self.frameNum-1][1]), interpolation=cv2.INTER_CUBIC)
#
#                 h,w,c = resizeImg.shape
#                 qImg = QImage(resizeImg.data,w,h,w*c, QImage.Format_RGB888)
#
#                 # 자른 사진 화면에 띄우기
#                 streamingPixmap = QPixmap.fromImage(qImg)
#                 picturePage1.pictureStreaming.setPixmap(streamingPixmap)
#                 #picturePage1.pictureStreaming.move(self.movePoint[self.frameNum - 1][0],self.movePoint[self.frameNum - 1][1])
#             else:
#                 print("streampicture x")
#
#             sleep(TIME_PER_FRAME)
#
#         self.cam.release()
#         print("cam off")
#
#     # 사진 찍고 저장
#     def takePicture(self, cnt):
#         global pictureFin
#         ret, img = self.cam.read()
#
#         if ret: # true : 사진 촬영 정상 작동시
#
#             # 찍은 사진 선택한 프레임에 맞게 자르기
#             if self.frameNum == 1:
#                 afterHeight = (self.resolution[0] * self.pictureRatio[self.frameNum - 1][1]) // \
#                               self.pictureRatio[self.frameNum - 1][0]
#                 temp = (self.resolution[1] - afterHeight) // 2
#                 img2 = img[temp:(self.resolution[1] - temp), :]
#             elif self.frameNum == 2:
#                 afterWidth = (self.resolution[1] * self.pictureRatio[self.frameNum - 1][0]) // \
#                              self.pictureRatio[self.frameNum - 1][1]
#                 temp = (self.resolution[0] - afterWidth) // 2
#                 img2 = img[:, temp:(self.resolution[0] - temp)]
#
#             # img = cv2.flip(img, 1)
#             cv2.imwrite('./picture/photo{}.jpg'.format(cnt), img2) # 사진 저장
#
#             self.cameraFlag = False
#
#             img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
#
#             resizeImg = cv2.resize(img2, (self.screenSize[self.frameNum-1][0], self.screenSize[self.frameNum-1][1]), interpolation=cv2.INTER_CUBIC)
#
#             h, w, c = resizeImg.shape
#             qImg = QImage(resizeImg.data, w, h, w * c, QImage.Format_RGB888)
#
#             # 자른 사진 화면에 띄우기
#             picturePixmap = QPixmap.fromImage(qImg)
#             picturePage1.pictureStreaming.setPixmap(picturePixmap)
#
#             sleep(1) # 1초간 화면 정지
#             pictureFin = True # 사진 촬영 종료 flag
#             if pictureFin:
#                 print("yes")
#             else:
#                 print("nonono")
#         else:
#             print("takepicture x")


#시작 페이지
class StartPage(QWidget):
    def __init__(self):
        super(StartPage, self).__init__()
        loadUi("StartPage.ui", self)

    def startButton(self):
       widget.setCurrentIndex(widget.currentIndex()+1)


# 프레임 선택 페이지
class SelectFramePage(QWidget):
    #선택한 프레임 번호
    selectNum = 0
    def __init__(self):
        super(SelectFramePage, self).__init__()
        loadUi("SelectFramePage.ui", self)

        # 선택한 사진 체크 표시 (hidden)
        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(True)

    # 1번 프레임 선택시
    def selectFrame1(self):
        self.frame2Check.setHidden(True)
        self.frame1Check.setHidden(False)
        self.selectNum = 1

    # 2번 프레임 선택시
    def selectFrame2(self):
        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(False)
        self.selectNum = 2

    # 페이지 변경
    def changePage(self):
        if self.selectNum != 0:
            self.frame1Check.setHidden(True)
            self.frame2Check.setHidden(True)
            widget.setCurrentIndex(widget.currentIndex()+1)


# 결제 페이지
class CostPage(QWidget):
    commandTimer = QtCore.pyqtSignal(int) # 사진 촬영 페이지 타이머 시작 신호
    commandCamera = QtCore.pyqtSignal(int) # 카메라 쓰레드 시작 신호
    def __init__(self):
        super(CostPage, self).__init__()
        loadUi("CostPage.ui", self)

    def changePage(self):
        self.commandTimer.emit(1)
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.commandCamera.emit(selectFramePage.selectNum) # 1, 2


# 사진 촬영 페이지ss
class PicturePage1(QWidget):
    command = QtCore.pyqtSignal(int)  # 몇번째 사진인지 알려주는 신호 (1,2,3,4)
    upload_command = QtCore.pyqtSignal(int)  # 사진 모두 찍은 이후 업로드 하기 위한 신호
    video_command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(PicturePage1, self).__init__()
        loadUi("PicturePage1.ui", self)
        self.time = 10  # 10초 타이머
        self.timer.setText(str(self.time))
        costPage.commandTimer.connect(self.createTimer)
        costPage.commandCamera.connect(self.showCamera)
        self.pictureCnt = 0  # 찍은 사진 갯수
        self.flag = 0
        self.click = 0
        self.button = Button(23)
        self.button.when_pressed = self.clickBtn
        #self.flagChange()

 #   def flagChange(self):
 #       VideoPage.flag = False

    def clickBtn(self):
        self.click = 1


    # 카메라 실행
    def showCamera(self, frameNum):
        # 카메라 쓰레드 실행
        self.camThread = CameraThread(frameNum)
        self.camThread.start()
        self.camThread.cameraFlag = True

        #self.remoconThread = RemoteControlThread()
        #self.remoconThread.start()


    # 타이머 생성 및 실행
    def createTimer(self):
        self.time = 11
        self.timerVar = QTimer()  # 타이머 생성
        self.timerVar.setInterval(1000)  # 타이머 간격 설정 (1초)
        self.timerVar.timeout.connect(self.printTime) # 1초마다 printTime() 실행
        self.timerVar.start()  # 타이머 실행

    # 타이머 화면 표시
    def printTime(self):
        self.time -= 1
        self.timer.setText(str(self.time))
        if self.time == 0:
            self.time = 11
            self.takePicture()
        if self.click == 1:
            self.time = 11
            self.takePicture()
            self.click = 0


    # 페이지 변경
    def changePage(self):
        self.camThread.cameraFlag = False
        print("cf false")
        self.camThread.cam.release()  # 카메라 종료
        self.timerVar.stop()  # 타이머 중지
        self.upload_command.emit(1)  # 사진 업로드 가능 신호
        self.video_command.emit(1)
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.cnt.setText("1")

    # 사진 촬영 (4번)
    def takePicture(self):
        global pictureFin
        if self.pictureCnt < 4:
            self.pictureCnt += 1
            self.cnt.setText(str(self.pictureCnt + 1))
            self.command.emit(self.pictureCnt)
            self.timerVar.stop()  # 타이머 멈춤
            while not pictureFin:  # 다음 타이머 실행 대기
                sleep(0.1)

            if self.pictureCnt < 4:
                print("1212")
                self.camThread.start()
                print("33434")# 실시간 화면 다시 시작
                self.createTimer()  # 타이머 다시 시작
                print("45454")
                self.camThread.cameraFlag = True
                print("67666")
                pictureFin = False
            else:
                self.pictureCnt = 0
                self.changePage()

# 사진 선택 페이지
class SelectPicturePage(QWidget):
    commandTimer = QtCore.pyqtSignal(int)  #
    commandPicture = QtCore.pyqtSignal(int, int)
    selectNum = 0
    def __init__(self):
        super(SelectPicturePage, self).__init__()
        loadUi("SelectPicturePage.ui", self)
        picturePage1.upload_command.connect(self.pictureUpload)
        self.picture1Check.setHidden(True)
        self.picture2Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(True)

    def selectPicture1(self):
        self.picture2Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(True)
        self.picture1Check.setHidden(False)
        self.selectNum = 1

    def selectPicture2(self):
        self.picture1Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(True)
        self.picture2Check.setHidden(False)
        self.selectNum = 2

    def selectPicture3(self):
        self.picture1Check.setHidden(True)
        self.picture2Check.setHidden(True)
        self.picture4Check.setHidden(True)
        self.picture3Check.setHidden(False)
        self.selectNum = 3

    def selectPicture4(self):
        self.picture1Check.setHidden(True)
        self.picture2Check.setHidden(True)
        self.picture3Check.setHidden(True)
        self.picture4Check.setHidden(False)
        self.selectNum = 4


    def pictureUpload(self):
        self.selectPicture1Btn.setStyleSheet("border-image:url('./picture/photo1.jpg')")
        self.selectPicture2Btn.setStyleSheet("border-image:url('./picture/photo2.jpg')")
        self.selectPicture3Btn.setStyleSheet("border-image:url('./picture/photo3.jpg')")
        self.selectPicture4Btn.setStyleSheet("border-image:url('./picture/photo4.jpg')")

        if selectFramePage.selectNum == 1:

            self.selectPicture1Btn.resize(474, 295)
            self.selectPicture1Btn.move(80,180)
            self.picture1Check.move(250,270)

            self.selectPicture2Btn.resize(474, 295)
            self.selectPicture2Btn.move(600, 180)
            self.picture2Check.move(780,270)

            self.selectPicture3Btn.resize(474, 295)
            self.selectPicture3Btn.move(80, 490)
            self.picture3Check.move(250,580)

            self.selectPicture4Btn.resize(474, 295)
            self.selectPicture4Btn.move(600, 490)
            self.picture4Check.move(780,580)

        elif selectFramePage.selectNum == 2:

            self.selectPicture1Btn.resize(269, 548)
            self.selectPicture1Btn.move(20, 190)
            self.picture1Check.move(100,400)

            self.selectPicture2Btn.resize(269, 548)
            self.selectPicture2Btn.move(300, 190)
            self.picture2Check.move(380,400)

            self.selectPicture3Btn.resize(269, 548)
            self.selectPicture3Btn.move(580, 190)
            self.picture3Check.move(660,400)

            self.selectPicture4Btn.resize(269, 548)
            self.selectPicture4Btn.move(860, 190)
            self.picture4Check.move(940,400)

    def changePage(self):
        if self.selectNum != 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.commandTimer.emit(1)
            self.commandPicture.emit(self.selectNum, selectFramePage.selectNum)
            self.picture1Check.setHidden(True)
            self.picture2Check.setHidden(True)
            self.picture3Check.setHidden(True)
            self.picture4Check.setHidden(True)


class BackgroundPage(QWidget):
    command = QtCore.pyqtSignal(int)
    selectBgNum = 0
    def __init__(self):
        super(BackgroundPage, self).__init__()
        loadUi("BackgroundPage.ui", self)
        # self.costTempBtn.clicked.connect(self.changePage)
        # 사진 찍는 시간 타이머
        #self.time = 61
        self.time = 60
        self.timer.setText(str(self.time))
        selectPicturePage.commandTimer.connect(self.createTimer)
        selectPicturePage.commandPicture.connect(self.uploadPicture)
        self.imageFrame1.setHidden(True)
        self.imageFrame2.setHidden(True)
        self.backgroundPageNum = 0
        self.uploadBackground()

    def createTimer(self):
        self.time = 61
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.timer.setText(str(self.time))
        if self.time == 0:
            self.changePage()

    def uploadPicture(self, pictureNum, frameNum):
        if frameNum == 1:
            self.imageFrame1.setHidden(False)
            self.imageFrame1.setStyleSheet("border-image:url('./picture/photo{}.jpg')".format(pictureNum))
        elif frameNum == 2:
            self.imageFrame2.setHidden(False)
            self.imageFrame2.setStyleSheet("border-image:url('./picture/photo{}.jpg')".format(pictureNum))

    def uploadBackground(self):
        self.bgImage1.setStyleSheet("border-image:url('./bgimage/bg{}.jpg');"
                                    "border-radius: 20px".format((self.backgroundPageNum*4) + 1 ))
        self.bgImage2.setStyleSheet("border-image:url('./bgimage/bg{}.jpg');"
                                    "border-radius: 20px".format((self.backgroundPageNum*4) + 2 ))
        self.bgImage3.setStyleSheet("border-image:url('./bgimage/bg{}.jpg');"
                                    "border-radius: 20px".format((self.backgroundPageNum*4) + 3 ))
        self.bgImage4.setStyleSheet("border-image:url('./bgimage/bg{}.jpg');"
                                    "border-radius: 20px".format((self.backgroundPageNum*4) + 4 ))

    def nextImage(self):
        self.backgroundPageNum += 1
        if self.backgroundPageNum > 3:
            self.backgroundPageNum = 3
        self.uploadBackground()

    def prevImage(self):
        self.backgroundPageNum -= 1
        if self.backgroundPageNum < 0:
            self.backgroundPageNum = 0
        self.uploadBackground()

    def selectBackground1(self):
        self.postImage.setStyleSheet("border-image:url('./bgimage/bg{}.jpg')".format((self.backgroundPageNum*4) + 1 ))
        self.selectBgNum = (self.backgroundPageNum*4) + 1

    def selectBackground2(self):
        self.postImage.setStyleSheet("border-image:url('./bgimage/bg{}.jpg')".format((self.backgroundPageNum * 4) + 2))
        self.selectBgNum = (self.backgroundPageNum * 4) + 2

    def selectBackground3(self):
        self.postImage.setStyleSheet("border-image:url('./bgimage/bg{}.jpg')".format((self.backgroundPageNum * 4) + 3))
        self.selectBgNum = (self.backgroundPageNum * 4) + 3

    def selectBackground4(self):
        self.postImage.setStyleSheet("border-image:url('./bgimage/bg{}.jpg')".format((self.backgroundPageNum * 4) + 4))
        self.selectBgNum = (self.backgroundPageNum * 4) + 4

    def changePage(self):
        if self.selectBgNum != 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.command.emit(1)
            self.timerVar.stop()
            self.imageFrame1.setHidden(True)
            self.imageFrame2.setHidden(True)
            self.backgroundPageNum = 0
            self.uploadBackground()
            self.postImage.setStyleSheet("background-color: rgb(255, 255, 255)")



class MemoPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    clear_command = QtCore.pyqtSignal(int)
    plus_command = QtCore.pyqtSignal(int)
    minus_command = QtCore.pyqtSignal(int)
    color_command = QtCore.pyqtSignal(int)
    save_command = QtCore.pyqtSignal(int)
    #pen_size = 5
    #pen_color = Qt.black
    def __init__(self):
        super(MemoPage, self).__init__()
        loadUi("MemoPage.ui", self)
        backgroundPage.command.connect(self.createTimer)
        self.memowidget = MyApp(self.clear_command, self.plus_command, self.minus_command, self.color_command, self.save_command)
        self.memowidget.resize(846,702)
        self.exlayout.addWidget(self.memowidget)
        self.flag = 1
        self.time = 180
        self.timer.setText(str(self.time))
        self.redBtn.setHidden(True)
        self.yellowBtn.setHidden(True)
        self.blueBtn.setHidden(True)
        self.blackBtn.setHidden(True)
        self.pinkBtn.setHidden(True)
        self.purpleBtn.setHidden(True)
        self.skyBtn.setHidden(True)
        self.imageFrame1.setHidden(True)
        self.imageFrame2.setHidden(True)


    def plusSize(self):
        self.plus_command.emit(1)

    def minusSize(self):
        self.minus_command.emit(1)

    def changeColor(self):
        if self.flag == 0:
            self.redBtn.setHidden(True)
            self.yellowBtn.setHidden(True)
            self.blueBtn.setHidden(True)
            self.blackBtn.setHidden(True)
            self.pinkBtn.setHidden(True)
            self.purpleBtn.setHidden(True)
            self.skyBtn.setHidden(True)
            self.flag = 1
        else:
            self.redBtn.setHidden(False)
            self.yellowBtn.setHidden(False)
            self.blueBtn.setHidden(False)
            self.blackBtn.setHidden(False)
            self.pinkBtn.setHidden(False)
            self.purpleBtn.setHidden(False)
            self.skyBtn.setHidden(False)
            self.flag = 0

    def changeRed(self):
        self.color_command.emit(1)

    def changeYellow(self):
        self.color_command.emit(2)

    def changeBlue(self):
        self.color_command.emit(3)

    def changeBlack(self):
        self.color_command.emit(4)

    def changePink(self):
        self.color_command.emit(5)

    def changePurple(self):
        self.color_command.emit(6)

    def changeSky(self):
        self.color_command.emit(7)

    def clear(self):
        self.clear_command.emit(1)

    def createTimer(self):
        self.time = 180
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()
        self.uploadBackground()


    def printTime(self):
        self.time -= 1
        self.timer.setText(str(self.time))
        if self.time == 0:
            #self.timerVar.stop()
            self.changePage()

    def uploadBackground(self):
        self.postBackground.setStyleSheet("border-image:url('./bgimage/bg{}.jpg')".format(backgroundPage.selectBgNum))
        if selectFramePage.selectNum == 1:
            self.imageFrame1.setHidden(False)
            self.imageFrame1.setStyleSheet("border-image:url('./picture/photo{}.jpg')".format(selectPicturePage.selectNum))
        elif selectFramePage.selectNum == 2:
            self.imageFrame2.setHidden(False)

            self.imageFrame2.setStyleSheet("border-image:url('./picture/photo{}.jpg')".format(selectPicturePage.selectNum))


    def changePage(self):
        self.save_command.emit(1)
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송
        self.timerVar.stop()
        self.memowidget.brush_size = 8
        self.memowidget.brush_color = Qt.black
        self.imageFrame1.setHidden(True)
        self.imageFrame2.setHidden(True)


class MyApp(QWidget):
    def __init__(self, clear_command, plus_command, minus_command, color_command, save_command):
        super().__init__()
        self.image = QImage(QSize(848, 698), QImage.Format_RGBA8888)
        #self.image.fill(Qt.white)
        self.image.fill(QColor(255,255,255,0))
        self.drawing = False
        clear_command.connect(self.clear_r)
        plus_command.connect(self.plusSize_r)
        minus_command.connect(self.minusSize_r)
        color_command.connect(self.color)
        save_command.connect(self.save)
        self.brush_size = 8
        self.brush_color = Qt.black
        self.last_point = QPoint()
        #self.initUI()
        ##self.cor = []
        ##self.sub_cor = []

    def color(self, num):
        if num == 1:
            self.brush_color = QColor(255, 18, 18)
        elif num == 2:
            self.brush_color = QColor(255, 228, 0)
        elif num == 3:
            self.brush_color = QColor(70, 65, 217)
        elif num == 4:
            self.brush_color = Qt.black
        elif num == 5:
            self.brush_color = QColor(255, 192, 203)
        elif num == 6:
            self.brush_color = QColor(255, 178, 245)
        elif num == 7:
            self.brush_color = QColor(178, 204, 255)

    def clear_r(self, num):
        self.image.fill(QColor(255,255,255,0))
        self.update()

    def plusSize_r(self):
        self.brush_size += 1

    def minusSize_r(self):
        if self.brush_size >= 5:
            self.brush_size -= 1
        else:
            self.brush_size = 5

    def paintEvent(self, e):
        canvas = QPainter(self)
        canvas.drawImage(self.rect(), self.image, self.image.rect())

    def mousePressEvent(self, e):
        if e.button() == Qt.LeftButton:
            self.drawing = True
            self.last_point = e.pos()

    def mouseMoveEvent(self, e):
        if (e.buttons() & Qt.LeftButton) & self.drawing:
            painter = QPainter(self.image)
            painter.setPen(QPen(self.brush_color, self.brush_size, Qt.SolidLine, Qt.RoundCap))
            painter.drawLine(self.last_point, e.pos())
            self.last_point = e.pos()
            self.update()

    def mouseReleaseEvent(self, e):
        if e.button() == Qt.LeftButton:
            self.drawing = False
            ##self.cor.append(self.sub_cor[:])

    def save(self):
        fpath = './memo/memo1.png'
        self.image.save(fpath, "PNG")
        self.image.fill(QColor(255,255,255,0))
        self.update()



class AddressPage(QWidget):
    command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(AddressPage, self).__init__()
        loadUi("AddressPage.ui", self)
        #self.dialog = QWidget()


    def findAddress(self):
        self.abc = AddressInput()

    def changePage(self):
        self.move(300, 300)
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송
        self.saveAddr()
        print(self.senderText)
        print(self.receiverText)
        print(self.addressNum)
        print(self.addressMain)
        print(self.addressDetail)
        self.addressImage = AddressImage()
        printPage.img.setStyleSheet("border-image:url('./final/final.jpg')")
        printPage.addr.setStyleSheet("border-image:url('./final/addr.jpg')")
        printPage.printStart()
        self.senderText = self.textEditSender.clear()
        self.receiverText = self.textEditReceiver.clear()
        self.addressNum = self.textEditNum.clear()
        self.addressMain = self.textEditAddress.clear()
        self.addressDetail = self.textEditDetail.clear()


    def saveAddr(self):
        self.senderText = self.textEditSender.toPlainText()
        self.receiverText = self.textEditReceiver.toPlainText()
        self.addressNum = self.textEditNum.toPlainText()
        self.addressMain = self.textEditAddress.toPlainText()
        self.addressDetail = self.textEditDetail.toPlainText()



class AddressInput(QWidget):
    def __init__(self):
        super(AddressInput, self).__init__()
        loadUi("AddressInput.ui", self)
        self.move(200, 200)
        self.show()
        self.text = ""
        self.newAddressListAreaCd = []
        self.check = 0
        self.chooseNum = 0
        self.addressArr = []
        self.addressNum = []
        self.listWidget.itemSelectionChanged.connect(self.selectChanged)
        #self.textEdit.setFontPointSize(12) # 폰트 사이즈 지정
        #self.textEdit.setFontPointSize(30)


    def search(self):
        self.text = self.textEdit.toPlainText()
        #self.textEdit.setFontPointSize(12) # 폰트 사이즈 지정
        #self.textEdit.setFontPointSize(30)
        print(self.text)
        findAddress()

        for r in self.newAddressListAreaCd:
            self.check += 1
            print(self.check)
            nowAddress = QListWidgetItem(f'선택 번호 : {self.check}\n 도로명 주소 : {r.findtext("lnmAdres")}\n 우편번호 : {r.findtext("zipNo")}\n')
            self.listWidget.addItem(nowAddress)
            # addressPage.abc.textEditOutput.append(f'선택 번호 : {self.check}\n'
            #                                       f'도로명 주소 : {r.findtext("lnmAdres")}\n'
            #                                       f'우편번호 : {r.findtext("zipNo")}\n'
            #                                       f'------------------------------------------------')
            self.addr = r.findtext("lnmAdres")
            self.num = r.findtext("zipNo")
            self.addressArr.append(self.addr)
            self.addressNum.append(self.num)
            #self.btn = QPushButton('선택', self)
            #self.btn.resize(30, 30)
            #self.btn.clicked.connect(self.inputAll)

    def selectChanged(self):
        #list_item = self.listWidget.selectedItems()
        self.selectedNum = self.listWidget.currentRow()
        print(self.selectedNum)



    def finish(self):
        self.chooseNum = self.selectedNum
        print(self.chooseNum)
        # if self.check == self.chooseNum :
        #     print(self.addr)
        #     print(self.num)
        print(self.addressArr[self.chooseNum])
        print(self.addressNum[self.chooseNum])
        addressPage.textEditNum.setText(self.addressNum[self.chooseNum])
        addressPage.textEditAddress.setText(self.addressArr[self.chooseNum])
        self.hide()


class AddressImage(QWidget):
    def __init__(self):
        super(AddressImage, self).__init__()

        loadUi("AddressImage.ui", self)
        self.fromAddress.setText('서울 강남구 테헤란로 212')
        self.fromName.setText(addressPage.senderText)
        self.fromNumber.setText('06220')
        self.toAddress.setText(addressPage.addressMain + " " + addressPage.addressDetail)
        self.toName.setText(addressPage.receiverText)
        self.toNumber.setText(addressPage.addressNum)
        self.compositeEvent()

        # self.add = QShortcut(QKeySequence("Ctrl+S"), self)
        # self.add.activated.connect(self.compositeEvent)

    def compositeEvent(self):
        pixmap = QPixmap(QSize(1152, 550))
        # pixmap.set_device_pixel_ratio(2)
        self.render(pixmap)
        pixmap.save("./final/addr.jpg", "jpg", -1)



class PrintPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(PrintPage, self).__init__()
        loadUi("PrintPage.ui", self)
        addressPage.command.connect(self.createTimer)
        #self.time = 3

    def createTimer(self):
        self.time = 4
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()
        finalImageSave()  # **********************************
        # self.img.setStyleSheet("border-image:url('./final/final.jpg')")
        # self.addr.setStyleSheet("border-image:url('./final/addr.jpg')")

    def printStart(self):
        os.system("lp -d Canon_SELPHY_CP1300 ./final/final.jpg")
        print("post print")
        os.system("lp -d Canon_G3010_series_1 ./final/addr.jpg")
        print("envelope print")

    def printTime(self):
        self.time -= 1
        print(self.time)
        if self.time == 0:
            self.changePage()

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex() + 1)
        self.timerVar.stop()
        self.command.emit(1)  # 신호 전송


class SelectPage(QWidget):
    command = QtCore.pyqtSignal(int)
    command2 = QtCore.pyqtSignal(int)#신호 생성
    def __init__(self):
        super(SelectPage, self).__init__()
        loadUi("SelectPage.ui", self)
        # self.videoBtn.clicked.connect(self.changePage)
        # self.skipBtn.clicked.connect(self.changePage2)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송

    def changePage2(self):
        widget.setCurrentIndex(widget.currentIndex()+3)
        self.command2.emit(1)


class ReadyPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(ReadyPage, self).__init__()
        loadUi("ReadyPage.ui", self)
        selectPage.command.connect(self.createTimer)
        # 영상 시간 timer
        self.time = 5
        self.timer.setText(str(self.time))

    def createTimer(self):
        self.time = 6
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.timer.setText(str(self.time))
        # self.Number.setText(str(self.time))
        if self.time == 0:
            self.changePage()

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex() + 1)
        self.timerVar.stop()
        self.command.emit(1)


class VideoPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성

    def __init__(self):
        super(VideoPage, self).__init__()
        loadUi("VideoPage.ui", self)
        readyPage.command.connect(self.createTimer)
        self.flag = False
        self.cnt = 0
        picturePage1.video_command.connect(self.changeFlag)  # picture page 넘어갈때
        self.button = Button(24)
        self.button.when_released = self.clearBtn
        #picturePage1.video_command.connect(self.changeFlag)  # picture page 넘어갈때
        # 영상 시간 timer

        self.click = 0
        self.time = 20
        self.timer.setText(str(self.time))

    def clearBtn(self):
        if self.cnt == 0:
            self.flag = False
            self.click = 0
            self.cnt = self.cnt + 1
            print(self.cnt)

    def changeFlag(self):
        print("r1")
        self.flag = True
        print("r2")
        self.button.when_pressed = self.clickBtn
        #self.flag = True

    def clickBtn(self):
        if self.flag:
            self.click = 1
            print("r3")
        else:
            self.click = 0
            print("r4")
        print(self.cnt)


    def createTimer(self):
        print("a")
        self.time = 21
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()
        print("b")
        self.showVideo()

    def printTime(self):
        self.time -= 1
        self.timer.setText(str(self.time))
        if self.time == 0:
            self.time = 21
            self.changePage()
        if self.click == 1:
            self.time = 21
            print("r5")
            self.changePage()
            self.click = 0
            print("r6")

    def showVideo(self):
        self.videoThread = VideoThread()
        self.videoThread.start()
        self.videoThread.videoFlag = True

    def changePage(self):
        global videoFin
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.videoThread.videoFlag = False
        self.timerVar.stop()
        while not videoFin:  # 다음 타이머 실행 대기
            sleep(0.1)
        self.command.emit(1)  # 신호 전송
        print("cf false")
        videoFin = False
        self.flag = False
        print("r7")
        self.cnt = 0
        #self.videoThread.cam.release()  # 카메라 종료


class FinishPage(QWidget):
    def __init__(self):
        super(FinishPage, self).__init__()
        loadUi("FinishPage.ui", self)
        selectPage.command2.connect(self.createTimer)
        videoPage.command.connect(self.createTimer)
        #self.time = 6

    def createTimer(self):
        self.time = 6
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()
        renameFile()

    def printTime(self):
        self.time -= 1
        if self.time == 0:
            widget.setCurrentIndex(0)
            self.timerVar.stop()






if __name__ == "__main__" :
    #QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv)



    #폰트 바꾸기
    # fontDB = QFontDatabase()
    # fontDB.addApplicationFont("./HSSaemaul-Regular.ttf")
    # app.setFont(QFont('HS새마을체'))


    #화면 전환용 Widget 설정
    widget = QtWidgets.QStackedWidget()

    #레이아웃 인스턴스 생성
    #startPage = StartPage()
    #selectFramePage = SelectFramePage()
    #costPage = CostPage()
    #picturePage1 = PicturePage1()
    #picturePage2 = PicturePage2()
    #selectPicturePage = SelectPicturePage()
    #backgroundPage = BackgroundPage()
    #memoPage = MemoPage()
    addressPage = AddressPage()
    #printPage = PrintPage()
    #selectPage = SelectPage()
    #readyPage = ReadyPage()
    #videoPage = VideoPage()
    #finishPage = FinishPage()



    #배경이미지
    # pixmap = QPixmap("./screenassets/background.png")
    # palette = QPalette()
    # palette.setBrush(QPalette.Background, QBrush(pixmap))
    # widget.setPalette(palette)

    #Widget 추가
    #widget.addWidget(startPage)
    #widget.addWidget(selectFramePage)
    #widget.addWidget(costPage)
    #widget.addWidget(picturePage1)
    #widget.addWidget(picturePage2)
    #widget.addWidget(selectPicturePage)
    #widget.addWidget(backgroundPage)
    #widget.addWidget(memoPage)
    widget.addWidget(addressPage)
    #widget.addWidget(printPage)
    #widget.addWidget(selectPage)
    #widget.addWidget(readyPage)
    #widget.addWidget(videoPage)
    #widget.addWidget(finishPage)

    #프로그램 화면을 보여주는 코드
    widget.setFixedHeight(796)
    widget.setFixedWidth(1152)
    widget.setStyleSheet("background-color: rgb(255, 221, 221);")
    widget.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()