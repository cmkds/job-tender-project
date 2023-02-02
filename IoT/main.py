from PyQt5 import QtWidgets, QtCore, QtGui
from PyQt5.QtWidgets import *
from PyQt5.uic import loadUi
from PyQt5.QtCore import *
import sys
import cv2
from time import sleep
from PyQt5.QtGui import *
from PyQt5.QtGui import QFontDatabase, QFont
import startres_rc
import image_rc

# 사진 촬영 완료 Flag
pictureFin = False

class CameraThread(QThread):
    def __init__(self , frameNum):
        super().__init__()
        picturePage1.command.connect(self.takePicture)
        self.frameNum = frameNum
        print(self.frameNum)
        # 카메라 상수
        self.resolution = [1280, 720]

        #frame1 138 : 70
        #frame2 55 : 93
        self.pictureRatio = [[138,70],[55,93]]
        self.screenSize = [
            [1000, 508],
            [414,700]
        ]

    def run(self):

        #frame1 가로 138 , 세로 70 -> 1000 * 508
        #frame2 가로 55 , 세로 93 -> 414 * 700

        picturePage1.pictureStreaming.resize(self.screenSize[self.frameNum-1][0],self.screenSize[self.frameNum-1][1])
        #picturePage1.pictureStreaming.resize(414, 700)
        self.cam = cv2.VideoCapture(0)

        self.cam.set(cv2.CAP_PROP_FRAME_WIDTH, self.resolution[0])
        self.cam.set(cv2.CAP_PROP_FRAME_HEIGHT, self.resolution[1])

        #self.cam.set(cv2.CAP_PROP_FRAME_WIDTH, 1600)
        #self.cam.set(cv2.CAP_PROP_FRAME_HEIGHT, 900)

        fps = self.cam.get(cv2.CAP_PROP_FPS)

        print(fps)

        if fps == 0.0:
            fps = 30.0

        TIME_PER_FRAME = 1 / fps

        while self.cameraFlag:
            ret, img = self.cam.read()
            #자르기

            if ret:
                img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
                # img[세로 , 가로]

                if self.frameNum == 1:
                    afterHeight = (self.resolution[0] * self.pictureRatio[self.frameNum - 1][1]) // self.pictureRatio[self.frameNum - 1][0]
                    temp = (self.resolution[1] - afterHeight) // 2
                    img2 = img[temp:(self.resolution[1] -temp), :]
                elif self.frameNum == 2:
                    afterWidth = (self.resolution[1] * self.pictureRatio[self.frameNum - 1][0]) // self.pictureRatio[self.frameNum-1][1]
                    temp = (self.resolution[0] - afterWidth) // 2
                    img2 = img[:, temp:(self.resolution[0] - temp)]


                resizeImg = cv2.resize(img2, (self.screenSize[self.frameNum-1][0], self.screenSize[self.frameNum-1][1]), interpolation=cv2.INTER_CUBIC)

                h,w,c = resizeImg.shape
                qImg = QImage(resizeImg.data,w,h,w*c, QImage.Format_RGB888)
                streamingPixmap = QPixmap.fromImage(qImg)
                picturePage1.pictureStreaming.setPixmap(streamingPixmap)
            else:
                print("streampicture x")

            sleep(TIME_PER_FRAME)
            print("sleeping")

        self.cam.release()
        print("cam off")

    def takePicture(self, cnt):
        global pictureFin
        ret, img = self.cam.read()

        if ret:
            if self.frameNum == 1:
                afterHeight = (self.resolution[0] * self.pictureRatio[self.frameNum - 1][1]) // \
                              self.pictureRatio[self.frameNum - 1][0]
                temp = (self.resolution[1] - afterHeight) // 2
                img2 = img[temp:(self.resolution[1] - temp), :]
            elif self.frameNum == 2:
                afterWidth = (self.resolution[1] * self.pictureRatio[self.frameNum - 1][0]) // \
                             self.pictureRatio[self.frameNum - 1][1]
                temp = (self.resolution[0] - afterWidth) // 2
                img2 = img[:, temp:(self.resolution[0] - temp)]

            # img = cv2.flip(img, 1)
            cv2.imwrite('./picture/photo{}.jpg'.format(cnt), img)
            cv2.imwrite('./picture/photo{}(2).jpg'.format(cnt), img2)

            self.cameraFlag = False

            img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

            resizeImg = cv2.resize(img2, (self.screenSize[self.frameNum-1][0], self.screenSize[self.frameNum-1][1]), interpolation=cv2.INTER_CUBIC)

            h, w, c = resizeImg.shape
            qImg = QImage(resizeImg.data, w, h, w * c, QImage.Format_RGB888)
            picturePixmap = QPixmap.fromImage(qImg)
            picturePage1.pictureStreaming.setPixmap(picturePixmap)
            print("사진{}".format(cnt))
            sleep(1)
            pictureFin = True
        else:
            print("takepicture x")



class StartPage(QWidget):
    def __init__(self):
        super(StartPage, self).__init__()
        loadUi("StartPage.ui", self)

    def startButton(self):
       widget.setCurrentIndex(widget.currentIndex()+1)


class SelectFramePage(QWidget):
    selectNum = 0
    def __init__(self):
        super(SelectFramePage, self).__init__()
        loadUi("SelectFramePage.ui", self)

        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(True)
        #self.selectFrame1Btn.clicked.connect(self.selectFrame1)
        #self.selectFrame2Btn.clicked.connect(self.selectFrame2)
        #self.nextBtn.clicked.connect(self.changePage)

    def selectFrame1(self):
        self.frame2Check.setHidden(True)
        self.frame1Check.setHidden(False)
        self.selectNum = 1

    def selectFrame2(self):
        self.frame1Check.setHidden(True)
        self.frame2Check.setHidden(False)
        self.selectNum = 2

    def changePage(self):
        if self.selectNum != 0:
            self.frame1Check.setHidden(True)
            self.frame2Check.setHidden(True)
            widget.setCurrentIndex(2)
        else:
            print("select X")


class CostPage(QWidget):
    commandTimer = QtCore.pyqtSignal(int)
    commandCamera = QtCore.pyqtSignal(int)

    def __init__(self):
        super(CostPage, self).__init__()
        loadUi("CostPage.ui", self)
        self.costFinish = False
        #self.costTempBtn.clicked.connect(self.changePage)

        if self.costFinish:
            self.changePage()

    def changePage(self):
        self.commandTimer.emit(selectFramePage.selectNum)
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.commandCamera.emit(selectFramePage.selectNum)




class PicturePage1(QWidget):
    command = QtCore.pyqtSignal(int)
    upload_command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(PicturePage1, self).__init__()
        loadUi("PicturePage1.ui",self)
        #self.costTempBtn.clicked.connect(self.changePage)
        self.lcdNumber.setDigitCount(2)
        costPage.commandTimer.connect(self.createTimer)
        costPage.commandCamera.connect(self.showCamera)

        self.pictureCnt = 0

    #카메라 쓰레드 실행


    def showCamera(self):
        self.camThread = CameraThread(selectFramePage.selectNum)
        self.camThread.start()
        self.camThread.cameraFlag = True

    def createTimer(self):
        self.time = 11
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.lcdNumber.display(self.time)
        #self.timer.setText(str(self.time))
        if self.time == 0:
            self.time = 11
            self.takePicture()
        print(self.time)

    def changePage(self):
        self.camThread.cameraFlag = False
        print("cf false")
        self.camThread.cam.release()
        #self.camThread.terminate()
        self.timerVar.stop()
        self.upload_command.emit(1)
        widget.setCurrentIndex(widget.currentIndex()+1)

    def takePicture(self):
        global pictureFin
        if self.pictureCnt < 4:
            self.pictureCnt += 1
            self.command.emit(self.pictureCnt)
            self.timerVar.stop()
            while not pictureFin:
                sleep(0.1)

            if self.pictureCnt < 4:
                self.camThread.start()
                self.createTimer()
                self.camThread.cameraFlag = True
                pictureFin = False
            else:
                self.pictureCnt = 0
                self.changePage()


class SelectPicturePage(QWidget):
    command = QtCore.pyqtSignal(int)
    selectNum = 0
    def __init__(self):
        super(SelectPicturePage, self).__init__()
        loadUi("SelectPicturePage.ui",self)
        picturePage1.upload_command.connect(self.pictureUpload)
        #self.image1 = QPixmap("./picture/photo1(2).jpg")
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
        self.selectPicture1Btn.setStyleSheet("border-image:url('./picture/photo1(2).jpg')")
        self.selectPicture1Btn.resize(414,210)
        self.selectPicture2Btn.setStyleSheet("border-image:url('./picture/photo2(2).jpg')")
        self.selectPicture2Btn.resize(414, 210)
        self.selectPicture3Btn.setStyleSheet("border-image:url('./picture/photo3(2).jpg')")
        self.selectPicture3Btn.resize(414, 210)
        self.selectPicture4Btn.setStyleSheet("border-image:url('./picture/photo4(2).jpg')")
        self.selectPicture4Btn.resize(414, 210)

    def changePage(self):
        if self.selectNum != 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.command.emit(1)
            self.picture1Check.setHidden(True)
            self.picture2Check.setHidden(True)
            self.picture3Check.setHidden(True)
            self.picture4Check.setHidden(True)



class BackgroundPage(QWidget):
    command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(BackgroundPage, self).__init__()
        loadUi("BackgroundPage.ui", self)
        # self.costTempBtn.clicked.connect(self.changePage)
        # 사진 찍는 시간 타이머
        #self.time = 61
        self.lcdNumber.setDigitCount(2)
        selectPicturePage.command.connect(self.createTimer)

    def createTimer(self):
        self.time = 61
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.lcdNumber.display(self.time)
        if self.time == 0:
            self.timerVar.stop()
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.command.emit(1)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1)


class MemoPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성

    def __init__(self):
        super(MemoPage, self).__init__()
        loadUi("MemoPage.ui", self)
        backgroundPage.command.connect(self.createTimer)


    def createTimer(self):
        self.time = 180
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        self.lcdNumber.display(self.time)
        if self.time == 0:
            self.timerVar.stop()
            self.changePage()

    def changePage(self):
        self.save_command.emit(1)
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송


class AddressPage(QWidget):
    command = QtCore.pyqtSignal(int)
    def __init__(self):
        super(AddressPage, self).__init__()
        loadUi("AddressPage.ui", self)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송


class PrintPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(PrintPage, self).__init__()
        loadUi("PrintPage.ui", self)
        addressPage.command.connect(self.createTimer)
        #self.time = 3

    def createTimer(self):
        self.time = 3
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        print(self.time)
        if self.time == 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.command.emit(1)  # 신호 전송
            self.timerVar.stop()


class SelectPage(QWidget):
    command = QtCore.pyqtSignal(int)
    command2 = QtCore.pyqtSignal(int)#신호 생성
    def __init__(self):
        super(SelectPage, self).__init__()
        loadUi("SelectPage.ui", self)
        #self.videoBtn.clicked.connect(self.changePage)
        #self.skipBtn.clicked.connect(self.changePage2)

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
        self.lcdNumber.setDigitCount(1)
        #self.time = 6

    def createTimer(self):
        self.time = 6
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        print(self.time)
        self.lcdNumber.display(self.time)
        # self.Number.setText(str(self.time))
        if self.time == 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.command.emit(1)
            self.timerVar.stop()


class VideoPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(VideoPage, self).__init__()
        loadUi("VideoPage.ui", self)
        readyPage.command.connect(self.createTimer)
        # 영상 시간 timer
        self.lcdNumber.setDigitCount(2)
        #self.time = 31
        # page 변환
        #self.nextBtn.clicked.connect(self.changePage)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1)  # 신호 전송
        self.timerVar.stop()

    def createTimer(self):
        print("a")
        self.time = 31
        self.timerVar = QTimer()
        #self.timerVar.timeout.connect(self.lcdEvent)
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()
        print("b")

    def printTime(self):
        self.time -= 1
        print(self.time)
        self.lcdNumber.display(self.time)
        if self.time == 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.timerVar.stop()


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
    startPage = StartPage()
    selectFramePage = SelectFramePage()
    costPage = CostPage()
    picturePage1 = PicturePage1()
    #picturePage2 = PicturePage2()
    selectPicturePage = SelectPicturePage()
    backgroundPage = BackgroundPage()
    memoPage = MemoPage()
    addressPage = AddressPage()
    printPage = PrintPage()
    selectPage = SelectPage()
    readyPage = ReadyPage()
    videoPage = VideoPage()
    finishPage = FinishPage()

    #배경이미지
    # pixmap = QPixmap("./screenassets/background.png")
    # palette = QPalette()
    # palette.setBrush(QPalette.Background, QBrush(pixmap))
    # widget.setPalette(palette)

    #Widget 추가
    widget.addWidget(startPage)
    widget.addWidget(selectFramePage)
    widget.addWidget(costPage)
    widget.addWidget(picturePage1)
    #widget.addWidget(picturePage2)
    widget.addWidget(selectPicturePage)
    widget.addWidget(backgroundPage)
    widget.addWidget(memoPage)
    widget.addWidget(addressPage)
    widget.addWidget(printPage)
    widget.addWidget(selectPage)
    widget.addWidget(readyPage)
    widget.addWidget(videoPage)
    widget.addWidget(finishPage)

    #프로그램 화면을 보여주는 코드
    widget.setFixedHeight(796)
    widget.setFixedWidth(1152)
    widget.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()