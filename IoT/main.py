import sys
from PyQt5 import QtWidgets
from PyQt5.QtWidgets import *
from PyQt5.uic import loadUi
import image

#class StartPage(QWidget):
#    def __init__(self):
#        super(StartPage, self).__init__()
#        loadUi("StartPage.ui", self)
#        self.startBtn.clicked.connect(self.startButton)

#    def startButton(self):
#        widget.setCurrentIndex(widget.currentIndex()+1)

class SelectPage(QWidget):
    def __init__(self):
        super(SelectPage, self).__init__()
        loadUi("SelectPage.ui", self)
        self.videoBtn.clicked.connect(self.changePage)
        self.skipBtn.clicked.connect(self.changePage2)

    def changePage(self):
        widget.setCurrentIndex(1)

    def changePage2(self):
        widget.setCurrentIndex(2)

class VideoPage(QWidget):
    def __init__(self):
        super(VideoPage, self).__init__()
        loadUi("VideoPage.ui", self)
        self.nextBtn.clicked.connect(self.changePage)

    def changePage(self):
        widget.setCurrentIndex(widget.currentIndex()+1)


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
#    startPage = StartPage()
    #frameSelectPage = FrameSelectPage()
    selectPage = SelectPage()
    videoPage = VideoPage()
    finishPage = FinishPage()

    #Widget 추가
#    widget.addWidget(startPage)
    #widget.addWidget(frameSelectPage)
    widget.addWidget(selectPage)
    widget.addWidget(videoPage)
    widget.addWidget(finishPage)

    #프로그램 화면을 보여주는 코드
    widget.setFixedHeight(600)
    widget.setFixedWidth(1024)
    widget.show()

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()