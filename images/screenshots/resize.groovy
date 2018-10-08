import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

// headless
System.setProperty("java.awt.headless", "true")

def convert = { File inputFile, File outputFile, int width->
    // イメージの読み込みとリサイズ
    def img = ImageIO.read(inputFile)
    def imgScaled = img.getScaledInstance(width,-1,Image.SCALE_SMOOTH)
    
    // リサイズ(スケール)されたイメージを直接 ImageIO.write() できないので、処理を追加
    def img2 = new BufferedImage((int)imgScaled.width,(int)imgScaled.height,BufferedImage.TYPE_4BYTE_ABGR)
    def g = img2.getGraphics();
    g.drawImage(imgScaled,0,0,null)
    g.dispose()
    
    // リサイズされたイメージを保存
    ImageIO.write(img2,'PNG',outputFile)
}

def srcDir = new File('src')
def dstDir = new File('.')
srcDir.listFiles().each {
	if( it.name.endsWith('png') ){
		convert( new File(srcDir, it.name), new File(dstDir, it.name), 480 )
	}
}
