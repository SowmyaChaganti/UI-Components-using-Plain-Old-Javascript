using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DictionaryTiles
{
    class ReadFile
    {
        List<String> allWords = new List<String>();
        public List<String> readFile()
        {

            String path = AppDomain.CurrentDomain.BaseDirectory;

            path = path.Replace("bin\\Debug\\", "dictionary.txt");
            System.IO.StreamReader file = new System.IO.StreamReader(path);
            String line;
            while((line = file.ReadLine()) != null)
            {
                //used list collection as this is best for accessing and reading
                allWords.Add(line);

            }

            return allWords;
        }
    }
}
