using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace DictionaryTiles
{
    class Program
    {
        static void Main(string[] args)
        {
            List<String> allWords = new List<string>();
            ReadFile read = new ReadFile();

            allWords = read.readFile();
            Console.WriteLine("Welcome to Dictionary!!");
            Console.WriteLine();
            Console.WriteLine("Enter seven tiles (tile: '_' Eg: a _ _ _ t _ m) : ");
            String tiles = Console.ReadLine();
            tiles = tiles.Replace(" ", "");
            tiles = tiles.Replace("_", "[a-z]?");

            Regex pattern = new Regex(@"^"+tiles+"$");


            foreach (String s in allWords)
            {
                if (pattern.IsMatch(s))
                {
                    Console.WriteLine(s);
                }
            }   
            

            Console.ReadLine();


        }
    }
}
