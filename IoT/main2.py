import sys
from PyQt5 import QtWidgets, QtCore
from PyQt5.QtWidgets import *
from PyQt5.uic import loadUi
from PyQt5.QtCore import *
import image

#class StartPage(QWidget):
#    def __init__(self):
#        super(StartPage, self).__init__()
#        loadUi("StartPage.ui", self)
#        self.startBtn.clicked.connect(self.startButton)

#    def startButton(self):
#        widget.setCurrentIndex(widget.currentIndex()+1)

class MemoPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(MemoPage, self).__init__()
        loadUi("MemoPage.ui", self)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        self.command.emit(1) #신호 전송

    def printTime(self):
        pass


class PrintPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(PrintPage, self).__init__()
        loadUi("PrintPage.ui", self)
        memoPage.command.connect(self.createTimer)
        self.time = 3

    def createTimer(self):
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
    command = QtCore.pyqtSignal(int) #신호 생성
    def __init__(self):
        super(SelectPage, self).__init__()
        loadUi("SelectPage.ui", self)
        self.videoBtn.clicked.connect(self.changePage)
        self.skipBtn.clicked.connect(self.changePage2)

    def changePage(self):
        print("hi")
        #self.command.emit(1)  # 신호 전송
        #print("hi2")
        widget.setCurrentIndex(3)
        print("hi2")
        self.command.emit(1) #신호 전송
        print("hi3")

    def changePage2(self):
        widget.setCurrentIndex(5)


class ReadyPage(QWidget):
    command = QtCore.pyqtSignal(int)  # 신호 생성
    def __init__(self):
        super(ReadyPage, self).__init__()
        loadUi("ReadyPage.ui", self)
        selectPage.command.connect(self.createTimer)
        # 영상 시간 timer
        self.lcdNumber.setDigitCount(1)
        self.time = 3

    def changePage(self):
        widget.setCurrentIndex(4)
        #self.timerVar.stop()
        self.command.emit(1) #신호 전송

    def createTimer(self):
        self.timerVar = QTimer()
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

    def printTime(self):
        self.time -= 1
        print(self.time)
        self.lcdNumber.display(self.time)
        if self.time == 0:
            widget.setCurrentIndex(widget.currentIndex()+1)
            self.timerVar.stop()


class VideoPage(QWidget):
    def __init__(self):
        super(VideoPage, self).__init__()
        loadUi("VideoPage.ui", self)
        readyPage.command.connect(self.createTimer)
        # 영상 시간 timer
        self.lcdNumber.setDigitCount(2)
        self.time = 31
        # page 변환
        self.nextBtn.clicked.connect(self.changePage)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)
        #self.timerVar.stop()

    def createTimer(self):
        self.timerVar = QTimer()
        #self.timerVar.timeout.connect(self.lcdEvent)
        self.timerVar.setInterval(1000)
        self.timerVar.timeout.connect(self.printTime)
        self.timerVar.start()

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


if __name__ == "__main__" :
    #QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv)

    #화면 전환용 Widget 설정
    widget = QtWidgets.QStackedWidget()

    #레이아웃 인스턴스 생성
#    startPage = StartPage()55
    memoPage = MemoPage()
    printPage = PrintPage()
    selectPage = SelectPage()
    readyPage = ReadyPage()
    videoPage = VideoPage()
    finishPage = FinishPage()

    #Widget 추가
#    widget.addWidget(startPage)
    widget.addWidget(memoPage)
    widget.addWidget(printPage)
    widget.addWidget(selectPage)
    widget.addWidget(readyPage)
    widget.addWidget(videoPage)
    widget.addWidget(finishPage)

    #프로그램 화면을 보여주는 코드
    widget.setFixedHeight(600)
    widget.setFixedWidth(1024)
    widget.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()