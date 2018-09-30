
html: index.html

all : index.html pdf

index.html : src/index.adoc src/user-guide.adoc
	asciidoctor -b html src/index.adoc -o index.html

pdf : coto-note-user-guide.pdf
coto-note-user-guide.pdf: src/index.adoc src/user-guide.adoc
	asciidoctor -r asciidoctor-pdf -b pdf src/index-pdf.adoc -o $@

clean :
	rm -f *.pdf index.html
