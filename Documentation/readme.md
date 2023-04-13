This documentation is written in LaTeX.  LaTex is a typesetting language that excels at technical papers.  
LaTex is especially good at typesetting mathmatical equations.
A LaTex distribution is needed to "compile" the LaTeX code into a PDF.
Extensions for Visual Studio Code are available to preview the document while editing.
LaTeX distributions for Windows are available at:  https://miktex.org/ and https://www.tug.org/texlive/windows.html

I use the texlive distribution and install everything from the ISO. https://www.tug.org/texlive/acquire-iso.html  

From PowerShell to download the ISO.
```
Invoke-WebRequest -OutFile texlive2023.iso -Uri https://mirrors.mit.edu/CTAN/systems/texlive/Images/texlive2023.iso
```

The easiest way is probably to run "winget install MiKTeX.MiKTeX" from a recent Windows 11 installation.  
This will install the MiKTeX distribution.

The goal of this is to document the Team60 command robot Java code for transitioning from
LabView.
